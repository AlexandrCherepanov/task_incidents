package com.cherepanov.task_incidents

import android.widget.Filter

class FilterItem(filterList: ArrayList<ModelIncident>, private val adapterSearch: AdapterSearch): Filter() {

    private val filterList: ArrayList<ModelIncident> = filterList

    override fun performFiltering(constraint: CharSequence?): FilterResults {
        var constraint1: CharSequence? = constraint
        val results = FilterResults()
        adapterSearch.queryText = constraint.toString()

        if(constraint1 != null && constraint1.isNotEmpty()){
            constraint1 = constraint1.toString().toLowerCase()
            val filteredModels = ArrayList<ModelIncident>()
            for (i in filterList.indices){
                if (filterList[i].DESCRIPTION.toLowerCase().contains(constraint1)){
                    filteredModels.add(filterList[i])
                }
            }
            results.count = filteredModels.size
            results.values = filteredModels
        }else{
            results.count = filterList.size
            results.values = filterList
        }
        return results
    }

    override fun publishResults(constraint: CharSequence, results: FilterResults) {
        adapterSearch.itemList = results.values as ArrayList<ModelIncident>
        adapterSearch.notifyDataSetChanged()
    }


}