package com.cherepanov.task_incidents

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.cherepanov.task_incidents.databinding.ActivityDetailsBinding
import kotlinx.android.synthetic.main.activity_details.*
import java.text.SimpleDateFormat


class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    private lateinit var itemList: ArrayList<ModelIncident>
    private var positionClicked:Int = 0

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(toolbarback)
        toolbarback.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        itemList = intent.getSerializableExtra("incident") as ArrayList<ModelIncident>
        positionClicked = intent.getIntExtra("position", 0)

        val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val formattedDateIsk = formatter.format(parser.parse(itemList[positionClicked].ISKNOWNERRORDATE))
        val formattedDateTar = formatter.format(parser.parse(itemList[positionClicked].TARGETFINISH))

        binding.descriptionT.text = itemList[positionClicked].DESCRIPTION + " (" + itemList[positionClicked].TICKETID + ")"
        binding.reportedbyT.text = itemList[positionClicked].REPORTEDBY
        binding.criticLevelT.text = itemList[positionClicked].CRITIC_LEVEL

        binding.isknownerrordateT.text = formattedDateIsk
        binding.targetfinishT.text = formattedDateTar

        binding.extsysnameT.text = itemList[positionClicked].EXTSYSNAME
        binding.statusT.text = itemList[positionClicked].STATUS
        binding.normT.text = itemList[positionClicked].NORM

        binding.lnormT.text = when(itemList[positionClicked].LNORM) {
                "0" -> getString(R.string.no_lnorm)
           else -> (itemList[positionClicked].LNORM)
        }



    }
}