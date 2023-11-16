package com.orkestra.paycars.controllers

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orkestra.paycars.R

class AdapterDataTransaction(context: Context, listData: List<DataModelTransaction>?) :
    RecyclerView.Adapter<AdapterDataTransaction.HolderData>() {

    private var context: Context
    private var inflater: LayoutInflater
    private var listData: List<DataModelTransaction>

    init {
        requireNotNull(listData) { "List data cannot be null" }
        this.listData = listData
        inflater = LayoutInflater.from(context)
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderData {
        val view: View = inflater.inflate(R.layout.item_data_transaction, parent, false)
        return HolderData(view)
    }

    override fun onBindViewHolder(holder: HolderData, position: Int) {
        if (position >= 0 && position < listData.size) {
            holder.nameTransaction.text = listData[position].nameTransaction
            Glide.with(context).load(listData[position].imageTransaction).into(holder.imageTransaction)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class HolderData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imageShowroomTransaction: ImageView = itemView.findViewById(R.id.imageShowroomTransaction)
        var nameShowroomTransaction: TextView = itemView.findViewById(R.id.nameShowroomTransaction)

        var imageTransaction: ImageView = itemView.findViewById(R.id.imageTransaction)
        var nameTransaction: TextView = itemView.findViewById(R.id.nameTransaction)
        var descriptionTransaction: TextView = itemView.findViewById(R.id.descriptionTransaction)

        var priceTransaction: TextView = itemView.findViewById(R.id.priceTransaction)

        var buttonStatusTransaction: TextView = itemView.findViewById(R.id.buttonStatusTransaction)
    }
}