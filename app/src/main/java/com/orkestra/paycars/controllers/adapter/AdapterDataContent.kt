package com.orkestra.paycars.controllers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orkestra.paycars.R
import com.orkestra.paycars.controllers.model.DataModelContent

class AdapterDataContent (private val onClickListener: (DataModelContent) -> Unit):
    ListAdapter<DataModelContent, AdapterDataContent.AdapterViewHolder>(ProductCallBack) {

    class AdapterViewHolder(itemView: View, val onClickListener: (DataModelContent) -> Unit):
            RecyclerView.ViewHolder(itemView) {

                private val thumbnail: ImageView = itemView.findViewById(R.id.imageContent)
                private val title: TextView = itemView.findViewById(R.id.nameContent)
                private val description: TextView = itemView.findViewById(R.id.descriptionContent)
                private val brand: TextView = itemView.findViewById(R.id.cityContent)
                private val price: TextView = itemView.findViewById(R.id.priceContent)

                private var currentProducts: DataModelContent? = null

                init {
                    itemView.setOnClickListener {
                        currentProducts?.let {
                            onClickListener(it)
                        }
                    }
                }

                fun bindDataContent(product: DataModelContent) {
                    currentProducts = product

                    title.text = product.title
                    description.text = product.description
                    brand.text = product.brand
                    price.text = product.price.toString()

                    Glide.with(itemView).load(product.thumbnail).centerCrop().into(thumbnail)
                }
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_content, parent, false)
        return AdapterViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val products = getItem(position)
        holder.bindDataContent(products)
    }
}

object ProductCallBack : DiffUtil.ItemCallback<DataModelContent> () {
    override fun areItemsTheSame(oldItem: DataModelContent, newItem: DataModelContent): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: DataModelContent, newItem: DataModelContent): Boolean {
        return oldItem.id == newItem.id
    }

}