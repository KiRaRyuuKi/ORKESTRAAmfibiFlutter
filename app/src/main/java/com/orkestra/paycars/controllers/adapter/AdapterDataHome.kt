package com.orkestra.paycars.controllers.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.orkestra.paycars.R
import com.orkestra.paycars.controllers.model.ModelDataHome
import com.orkestra.paycars.controllers.view.ViewDataHome
import com.orkestra.paycars.databinding.ItemDataBannerBinding
import com.orkestra.paycars.databinding.ItemDataContentBinding
import com.orkestra.paycars.databinding.ItemDataSellerBinding
import com.orkestra.paycars.databinding.ItemDataSubtitleBinding
import com.orkestra.paycars.databinding.ItemDataTitleBinding

sealed class AdapterDataHome: RecyclerView.Adapter<ModelDataHome>() {

    var items = listOf<ViewDataHome>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelDataHome {
        return when(viewType){
            R.layout.item_data_title -> ModelDataHome.TitleViewHolder(
                ItemDataTitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_data_banner -> ModelDataHome.BannerViewHolder(
                ItemDataBannerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_data_subtitle -> ModelDataHome.SubtitleViewHolder(
                ItemDataSubtitleBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_data_seller -> ModelDataHome.SellerViewHolder(
                ItemDataSellerBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.item_data_content -> ModelDataHome.ContentViewHolder(
                ItemDataContentBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> throw IllegalArgumentException("Invalid ViewType Provided")
        }
    }

    override fun onBindViewHolder(holder: ModelDataHome, position: Int) {
        when(holder){
            is ModelDataHome.TitleViewHolder -> holder.bind(items[position] as ViewDataHome.Title)
            is ModelDataHome.BannerViewHolder -> holder.bind(items[position] as ViewDataHome.Banner)
            is ModelDataHome.SubtitleViewHolder -> holder.bind(items[position] as ViewDataHome.Subtitle)
            is ModelDataHome.SellerViewHolder -> holder.bind(items[position] as ViewDataHome.Seller)
            is ModelDataHome.ContentViewHolder -> holder.bind(items[position] as ViewDataHome.Content)
        }
    }

    override fun getItemCount() = items.size

    override fun getItemViewType(position: Int): Int {
        return when(items[position]){
            is ViewDataHome.Title -> R.layout.item_data_title
            is ViewDataHome.Banner -> R.layout.item_data_banner
            is ViewDataHome.Subtitle -> R.layout.item_data_subtitle
            is ViewDataHome.Seller -> R.layout.item_data_seller
            is ViewDataHome.Content -> R.layout.item_data_content
        }
    }
}