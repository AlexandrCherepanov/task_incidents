package com.cherepanov.task_incidents

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.cherepanov.task_incidents.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray
import java.io.IOException
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var searchAdapter: AdapterSearch
    private lateinit var adapterItem: AdapterIncident
    private lateinit var itemList: ArrayList<ModelIncident>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadRecyclerViewItems()

        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            Toast.makeText(this, getString(R.string.thanks), Toast.LENGTH_LONG).show()
        }

    }

    private fun loadRecyclerViewItems() {

        var json: String? = null

        try {
            val inputStream: InputStream = assets.open("incidents.json")
            json = inputStream.bufferedReader().use { it.readText() }

            val jsonarr = JSONArray(json)
            itemList = ArrayList()

            for (i in 0..jsonarr.length()-1){
                val jsonobj = jsonarr.getJSONObject(i)

                val model = ModelIncident(jsonobj.getString("EXTSYSNAME"), jsonobj.getString("DESCRIPTION"),
                    jsonobj.getString("TICKETID"), jsonobj.getString("ISKNOWNERRORDATE"),
                    jsonobj.getString("TARGETFINISH"), jsonobj.getString("STATUS"),
                    jsonobj.getString("REPORTEDBY"), jsonobj.getString("CLASSIDMAIN"),
                    jsonobj.getString("CRITIC_LEVEL"), jsonobj.getString("NORM"),
                    jsonobj.getString("LNORM"))
                itemList.add(model)
            }

            adapterItem = AdapterIncident(this, itemList)
            binding.incidentsRv.adapter = adapterItem

        }catch (e: IOException){
            e.printStackTrace()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        val searchItem: MenuItem? = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as androidx.appcompat.widget.SearchView

        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                searchAdapter.filter.filter(newText)
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val itemView = item.itemId
        when(itemView){
            R.id.search -> {
                searchAdapter = AdapterSearch(this, itemList)
                binding.incidentsRv.adapter = searchAdapter
            }
        }
        return false
    }
}



















