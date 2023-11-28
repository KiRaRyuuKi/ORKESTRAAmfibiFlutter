package com.orkestra.paycars.controllers.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.orkestra.paycars.R
import com.orkestra.paycars.controllers.model.ModelDataItem
import com.orkestra.paycars.controllers.view.ViewDataBanner
import com.orkestra.paycars.controllers.view.ViewDataItem
import com.orkestra.paycars.controllers.view.ViewDataListItem
import com.orkestra.paycars.databinding.BannerItemBinding
import com.orkestra.paycars.databinding.EachItemBinding

class AdapterModelMainItem(private val dataItemList: List<ModelDataItem>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    inner class BannerItemViewHolder(private val binding: BannerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindBannerView(banner: ViewDataBanner) {
            binding.bannerIv.setImageResource(banner.image)
        }
    }

    inner class RecyclerItemViewHolder(private val binding: EachItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.childRecyclerView.setHasFixedSize(true)
            binding.childRecyclerView.layoutManager =
                LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
        }

        fun bindCarsRecyclerView(recyclerItemList: List<ViewDataItem>) {
            val adapter = AdapterModelChildItem(ViewDataListItem.CARS, recyclerItemList)
            binding.childRecyclerView.adapter = adapter
        }

        fun bindBestSellerRecyclerView(recyclerItemList: List<ViewDataItem>) {

            val snapHelper = PagerSnapHelper()
            snapHelper.attachToRecyclerView(binding.childRecyclerView)
            val adapter = AdapterModelChildItem(ViewDataListItem.BEST_SELLER, recyclerItemList)
            binding.childRecyclerView.adapter = adapter
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataItemList[position].viewType) {
            ViewDataListItem.BANNER -> R.layout.item_data_banner
            else -> R.layout.activity_main_each
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            R.layout.item_data_banner -> {
                val binding =
                    BannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                BannerItemViewHolder(binding)
            }
            else -> {
                val binding =
                    EachItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                RecyclerItemViewHolder(binding)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataItemList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BannerItemViewHolder -> {
                dataItemList[position].banner?.let { holder.bindBannerView(it) }
            }
            is RecyclerItemViewHolder -> {
                when (dataItemList[position].viewType) {
                    ViewDataListItem.BEST_SELLER -> {
                        dataItemList[position].recyclerViewItem?.let {
                            holder.bindBestSellerRecyclerView(it)
                        }
                    }
                    else -> {
                        dataItemList[position].recyclerViewItem?.let {
                            holder.bindCarsRecyclerView(it)
                        }
                    }
                }
            }
        }
    }
}