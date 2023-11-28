package com.orkestra.paycars.controllers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.orkestra.paycars.controllers.view.ViewDataItem
import com.orkestra.paycars.controllers.view.ViewDataListItem
import com.orkestra.paycars.databinding.BestSellerLayoutBinding
import com.orkestra.paycars.databinding.ClothingLayoutBinding

class AdapterModelChildItem(val viewType: Int, private val recyclerItemList: List<ViewDataItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class BestSellerViewHolder(private val binding: BestSellerLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindBestSellerView(recyclerItem: ViewDataItem) {
            binding.bestSellerImage.setImageResource(recyclerItem.image)
            binding.bestSellerText.text = recyclerItem.offer
        }
    }

    inner class ClothingViewHolder(private val binding: ClothingLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindCarsView(recyclerItem: ViewDataItem) {
            binding.clothingImage.setImageResource(recyclerItem.image)
            binding.clothingOfferTv.text = recyclerItem.offer
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewType
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = when (viewType) {
            ViewDataListItem.BEST_SELLER -> {
                BestSellerLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
            else -> {
                ClothingLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            }
        }
        return if (viewType == ViewDataListItem.BEST_SELLER) {
            BestSellerViewHolder(binding as BestSellerLayoutBinding)
        } else {
            ClothingViewHolder(binding as ClothingLayoutBinding)
        }
    }

    override fun getItemCount(): Int {
        return recyclerItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (holder) {

            is BestSellerViewHolder -> {
                holder.bindBestSellerView(recyclerItemList[position])
            }

            is ClothingViewHolder -> {
                holder.bindCarsView(recyclerItemList[position])
            }
        }
    }
}