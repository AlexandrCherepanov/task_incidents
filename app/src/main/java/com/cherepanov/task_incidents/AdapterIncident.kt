package com.cherepanov.task_incidents

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.cherepanov.task_incidents.databinding.IncidentItemBinding
import java.text.SimpleDateFormat

class AdapterIncident (
   private var context: Context,
   private var itemList: ArrayList<ModelIncident>
): RecyclerView.Adapter<AdapterIncident.HolderIncident>(){

    private lateinit var binding: IncidentItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderIncident {
        binding = IncidentItemBinding.inflate(LayoutInflater.from(context), parent, false)

        return HolderIncident(binding.root)
    }

    @SuppressLint("SetTextI18n", "SimpleDateFormat")
    override fun onBindViewHolder(holder: HolderIncident, position: Int) {
        val model = itemList[position]

        val ext = model.EXTSYSNAME
        val des: String = model.DESCRIPTION
        val tic: String = model.TICKETID
        val isk: String = model.ISKNOWNERRORDATE
        val tar: String = model.TARGETFINISH
        val sta: String = model.STATUS
        val rep: String = model.REPORTEDBY
        val cla: String = model.CLASSIDMAIN
        val cri: String = model.CRITIC_LEVEL
        val nor: String = model.NORM
        val lno: String = model.LNORM

        val parser =  SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
        val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm")
        val formattedDateIsk = formatter.format(parser.parse(isk))
        val formattedDateTar = formatter.format(parser.parse(tar))

        holder.extsysname.text = ext
        holder.decription.text = "$des ($tic)"
        holder.isknownerrordate.text = formattedDateIsk
        holder.targetfinish.text = formattedDateTar
        holder.status.text = sta

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

        var extsysname: TextView
        var decription: TextView
        var isknownerrordate: TextView
        var targetfinish: TextView
        var status: TextView

        init {
            binding = IncidentItemBinding.bind(itemView)

            extsysname = binding.extsysnameText
            decription = binding.descriptionText
            isknownerrordate = binding.isknownerrordateText
            targetfinish = binding.targetfinishText
            status = binding.statusText
        }
    }
 }