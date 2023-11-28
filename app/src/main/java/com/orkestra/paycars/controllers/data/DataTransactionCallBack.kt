package com.orkestra.paycars.controllers.data

import androidx.recyclerview.widget.DiffUtil
import com.orkestra.paycars.controllers.model.ModelDataTransaction

object DataTransactionCallBack : DiffUtil.ItemCallback<ModelDataTransaction> () {
    override fun areItemsTheSame(oldItem: ModelDataTransaction, newItem: ModelDataTransaction): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: ModelDataTransaction, newItem: ModelDataTransaction): Boolean {
        return oldItem.id == newItem.id
    }
}