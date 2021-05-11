package com.cherepanov.task_incidents

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.text.Spannable
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.cherepanov.task_incidents.databinding.DetailSearchBinding

class AdapterSearch : RecyclerView.Adapter<AdapterSearch.HolderIncident>, Filterable {

    private var context: Context
    private var filterItem: ArrayList<ModelIncident>
    private var filter: FilterItem? = null
    var queryText: String = ""
    var itemList: ArrayList<ModelIncident>

    constructor(context: Context, itemList: ArrayList<ModelIncident>) : super() {
        this.context = context
        this.itemList = itemList
        this.filterItem = itemList
    }

    private lateinit var binding: DetailSearchBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderIncident {
        binding = DetailSearchBinding.inflate(LayoutInflater.from(context), parent, false)
        return HolderIncident(binding.root)
    }

    override fun onBindViewHolder(holder: HolderIncident, position: Int) {

        val model = itemList[position]
        val des: String = model.DESCRIPTION

        if(queryText.isNotEmpty()){
            val startPos = des.toLowerCase().indexOf(queryText.toLowerCase())
            val endPos = startPos + queryText.length
            if(startPos!=-1){
                val spannable: Spannable = SpannableString(des)
                val bColor = BackgroundColorSpan(Color.YELLOW)
                spannable.setSpan(bColor, startPos, endPos, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
                holder.decription.text = spannable
            }else{
                holder.decription.text = des
            }
        }else{
            holder.decription.text = des
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, DetailsActivity::class.java)
            intent.putExtra("incident", itemList)
            intent.putExtra("position", position)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class HolderIncident(itemView: View): RecyclerView.ViewHolder(itemView){

        var decription: TextView

        init {
            binding = DetailSearchBinding.bind(itemView)
            decription = binding.descriptionItem
        }
    }

    override fun getFilter(): Filter {
        if (filter == null){
            filter = FilterItem(filterItem, this)
        }
        return filter as FilterItem
    }


}