package com.orkestra.paycars.controllers.model

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.orkestra.paycars.controllers.view.ViewDataHome
import com.orkestra.paycars.databinding.ItemDataBannerBinding
import com.orkestra.paycars.databinding.ItemDataContentBinding
import com.orkestra.paycars.databinding.ItemDataSellerBinding
import com.orkestra.paycars.databinding.ItemDataSubtitleBinding
import com.orkestra.paycars.databinding.ItemDataTitleBinding

sealed class ModelDataHome(binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

    class TitleViewHolder(private val binding: ItemDataTitleBinding) : ModelDataHome(binding){
        fun bind(title: ViewDataHome.Title){
            binding.titlePromo.text = title.title
            binding.titleDate.text = title.date
        }
    }

    class BannerViewHolder(private val binding: ItemDataBannerBinding) : ModelDataHome(binding){
        fun bind(banner: ViewDataHome.Banner){
//            binding.imageBanner.loadImage(banner.thumbnail)
        }
    }

    class SubtitleViewHolder(private val binding: ItemDataSubtitleBinding) : ModelDataHome(binding){
        fun bind(subtitle: ViewDataHome.Subtitle){
            binding.subtitleName.text = subtitle.subtitle
        }
    }

    class SellerViewHolder(private val binding: ItemDataSellerBinding) : ModelDataHome(binding){
        fun bind(seller: ViewDataHome.Seller){
//            binding.imageSeller.loadImage(seller.thumbnail)
            binding.nameSeller.text = seller.title
            binding.descriptionSeller.text = seller.description
        }
    }

    class ContentViewHolder(private val binding: ItemDataContentBinding) : ModelDataHome(binding){
        fun bind(content: ViewDataHome.Content){
//            binding.imageContent.loadImage(content.thumbnail)
            binding.nameContent.text = content.title
            binding.descriptionContent.text = content.description
            binding.brandContent.text = content.brand
            binding.priceContent.text = content.price.toString()
        }
    }
}
