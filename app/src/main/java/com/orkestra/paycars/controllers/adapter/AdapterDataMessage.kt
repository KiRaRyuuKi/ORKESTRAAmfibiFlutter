package com.orkestra.paycars.controllers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orkestra.paycars.R
import com.orkestra.paycars.controllers.model.ModelDataMessage

class AdapterDataMessage(context: Context, listData: List<ModelDataMessage>?) :
    RecyclerView.Adapter<AdapterDataMessage.HolderData>() {

    var listData: List<ModelDataMessage>
    var inflater: LayoutInflater
    var context: Context

    init {
        requireNotNull(listData) { "List data cannot be null" }
        this.listData = listData
        inflater = LayoutInflater.from(context)
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderData {
        val view: View = inflater.inflate(R.layout.item_data_message, parent, false)
        return HolderData(view)
    }

    override fun onBindViewHolder(holder: HolderData, position: Int) {
        if (position >= 0 && position < listData.size) {
            holder.textData.text = listData[position].header
            Glide.with(context).load(listData[position].image).into(holder.image)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class HolderData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textData: TextView = itemView.findViewById(R.id.headerMessage)
        var image: ImageView = itemView.findViewById(R.id.imageMessage)
    }
}
