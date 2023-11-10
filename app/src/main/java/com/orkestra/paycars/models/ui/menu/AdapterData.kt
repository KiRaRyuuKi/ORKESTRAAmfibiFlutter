package com.orkestra.paycars.models.ui.menu

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orkestra.paycars.R

class AdapterData(context: Context, listData: List<DataModel>?) :
    RecyclerView.Adapter<AdapterData.HolderData>() {

    var listData: List<DataModel>
    var inflater: LayoutInflater
    var context: Context

    init {
        requireNotNull(listData) { "List data cannot be null" }
        this.listData = listData
        inflater = LayoutInflater.from(context)
        this.context = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolderData {
        val view: View = inflater.inflate(R.layout.item_data, parent, false)
        return HolderData(view)
    }

    override fun onBindViewHolder(holder: HolderData, position: Int) {
        if (position >= 0 && position < listData.size) {
            holder.txtData.text = listData[position].judul
            Glide.with(context).load(listData[position].gambar).into(holder.img)
        }
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    inner class HolderData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txtData: TextView = itemView.findViewById(R.id.dataText)
        var img: ImageView = itemView.findViewById(R.id.idImg)
    }
}
