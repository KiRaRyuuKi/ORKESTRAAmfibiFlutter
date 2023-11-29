package com.orkestra.paycars.controllers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.orkestra.paycars.R
import com.orkestra.paycars.controllers.data.DataContentCallBack
import com.orkestra.paycars.controllers.model.ModelDataContent
import java.util.Locale

class AdapterDataContent(private val onClickListener: (ModelDataContent) -> Unit) :
    ListAdapter<ModelDataContent, AdapterDataContent.AdapterViewHolder>(DataContentCallBack) {

    class AdapterViewHolder(itemView: View, val onClickListener: (ModelDataContent) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val thumbnail: ImageView = itemView.findViewById(R.id.imageContent)
        private val title: TextView = itemView.findViewById(R.id.nameContent)
        private val description: TextView = itemView.findViewById(R.id.descriptionContent)
        private val brand: TextView = itemView.findViewById(R.id.brandContent)
        private val price: TextView = itemView.findViewById(R.id.priceContent)

        private var currentProducts: ModelDataContent? = null

        init {
            itemView.setOnClickListener {
                currentProducts?.let {
                    onClickListener(it)
                }
            }
        }

        fun bindDataContent(product: ModelDataContent) {
            currentProducts = product

            title.text = product.title
            description.text = product.description
            brand.text = product.brand
            price.text = product.price.toString()

            Glide.with(itemView).load(product.thumbnail).centerCrop().into(thumbnail)
        }
    }

    private var originalData: List<ModelDataContent> = emptyList()

    fun setData(data: List<ModelDataContent>) {
        submitList(data)
        originalData = data.toList()
    }

    fun searchDataContent(query: String) {
        val filteredList = if (query.isNotEmpty()) {
            originalData.filter {
                it.title.lowercase(Locale.ROOT).contains(query.lowercase(Locale.ROOT))
            }
        } else {
            originalData
        }
        submitList(filteredList)
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