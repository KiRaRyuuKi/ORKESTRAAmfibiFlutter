package com.orkestra.paycars.controllers.adapter
//
//import android.view.LayoutInflater
//import android.view.ViewGroup
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.PagerSnapHelper
//import androidx.recyclerview.widget.RecyclerView
//import com.example.multiplerecyclerviewmultiviewtype.databinding.BannerItemBinding
//import com.example.multiplerecyclerviewmultiviewtype.databinding.EachItemBinding
//import com.orkestra.paycars.R
//import com.orkestra.paycars.controllers.model.Banner
//import com.orkestra.paycars.controllers.model.DataModelItem
//import com.orkestra.paycars.controllers.model.RecyclerItem
//import com.orkestra.paycars.controllers.model.view.ViewListItem
//
//class AdapterModelMainItem(private val dataItemList: List<DataModelItem>) :
//    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
//
//    inner class BannerItemViewHolder(private val binding: BannerItemBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        fun bindBannerView(banner: Banner) {
//            binding.bannerIv.setImageResource(banner.image)
//        }
//    }
//
//    inner class RecyclerItemViewHolder(private val binding: EachItemBinding) :
//        RecyclerView.ViewHolder(binding.root) {
//
//        init {
//            binding.childRecyclerView.setHasFixedSize(true)
//            binding.childRecyclerView.layoutManager =
//                LinearLayoutManager(binding.root.context, RecyclerView.HORIZONTAL, false)
//        }
//
//        fun bindClothingRecyclerView(recyclerItemList: List<RecyclerItem>) {
//            val adapter = AdapterModelChildItem(ViewListItem.CARS, recyclerItemList)
//            binding.childRecyclerView.adapter = adapter
//        }
//
//        fun bindBestSellerRecyclerView(recyclerItemList: List<RecyclerItem>) {
//
//            val snapHelper = PagerSnapHelper()
//            snapHelper.attachToRecyclerView(binding.childRecyclerView)
//            val adapter = AdapterModelChildItem(ViewListItem.BEST_SELLER, recyclerItemList)
//            binding.childRecyclerView.adapter = adapter
//        }
//    }
//
//    override fun getItemViewType(position: Int): Int {
//        return when (dataItemList[position].viewType) {
//            ViewListItem.BANNER ->
//                R.layout.banner_item
//            else ->
//                R.layout.each_item
//        }
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//        return when (viewType) {
//            R.layout.banner_item -> {
//                val binding =
//                    BannerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//                BannerItemViewHolder(binding)
//            }
//            else -> {
//                val binding =
//                    EachItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
//                RecyclerItemViewHolder(binding)
//            }
//        }
//    }
//
//    override fun getItemCount(): Int {
//        return dataItemList.size
//    }
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//
//        when (holder) {
//            is BannerItemViewHolder -> {
//                dataItemList[position].banner?.let { holder.bindBannerView(it) }
//            }
//            else -> {
//                when (dataItemList[position].viewType) {
//                    ViewListItem.BEST_SELLER -> {
//                        dataItemList[position].recyclerItemList?.let {
//                            (holder as RecyclerItemViewHolder).bindBestSellerRecyclerView(
//                                it
//                            )
//                        }
//                    }
//                    else -> {
//                        dataItemList[position].recyclerItemList?.let {
//                            (holder as RecyclerItemViewHolder).bindClothingRecyclerView(
//                                it
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//
//}