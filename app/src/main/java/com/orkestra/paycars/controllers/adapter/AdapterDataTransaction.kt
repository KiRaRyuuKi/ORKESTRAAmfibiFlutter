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
import com.orkestra.paycars.controllers.data.DataTransactionCallBack
import com.orkestra.paycars.controllers.model.ModelDataTransaction
import java.util.Locale

class AdapterDataTransaction(private val onClickListener: (ModelDataTransaction) -> Unit) :
    ListAdapter<ModelDataTransaction, AdapterDataTransaction.AdapterViewHolder>(DataTransactionCallBack) {

    class AdapterViewHolder(itemView: View, val onClickListener: (ModelDataTransaction) -> Unit) :
        RecyclerView.ViewHolder(itemView) {

        private val thumbnail: ImageView = itemView.findViewById(R.id.imageTransaction)
        private val title: TextView = itemView.findViewById(R.id.nameTransaction)
        private val description: TextView = itemView.findViewById(R.id.descriptionTransaction)
        private val price: TextView = itemView.findViewById(R.id.priceTransaction)

        private var currentProducts: ModelDataTransaction? = null

        init {
            itemView.setOnClickListener {
                currentProducts?.let {
                    onClickListener(it)
                }
            }
        }

        fun bindDataContent(product: ModelDataTransaction) {
            currentProducts = product

            title.text = product.title
            description.text = product.description
            price.text = product.price.toString()

            Glide.with(itemView).load(product.thumbnail).centerCrop().into(thumbnail)
        }
    }

    private var originalData: List<ModelDataTransaction> = emptyList()

    fun setData(data: List<ModelDataTransaction>) {
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data_transaction, parent, false)
        return AdapterViewHolder(view, onClickListener)
    }

    override fun onBindViewHolder(holder: AdapterViewHolder, position: Int) {
        val products = getItem(position)
        holder.bindDataContent(products)
    }
}