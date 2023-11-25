package com.orkestra.paycars.controllers.data

import androidx.recyclerview.widget.DiffUtil
import com.orkestra.paycars.controllers.model.ModelDataContent

object DataContentCallBack : DiffUtil.ItemCallback<ModelDataContent> () {
    override fun areItemsTheSame(oldItem: ModelDataContent, newItem: ModelDataContent): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ModelDataContent, newItem: ModelDataContent): Boolean {
        return oldItem.id == newItem.id
    }
}