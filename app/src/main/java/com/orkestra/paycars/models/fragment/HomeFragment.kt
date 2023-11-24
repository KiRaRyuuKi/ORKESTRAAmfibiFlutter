package com.orkestra.paycars.models.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil.setContentView
import androidx.recyclerview.widget.LinearLayoutManager
import com.orkestra.paycars.R
//import com.orkestra.paycars.controllers.adapter.AdapterModelMainItem
//import com.orkestra.paycars.controllers.model.DataModelItem

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
//    private lateinit var dataBinding: ActivityMainBinding
//    private lateinit var modelListItem: ArrayList<DataModelItem>

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

//        dataBinding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(dataBinding.root)
//
//
//        dataBinding.mainRecyclerView.setHasFixedSize(true)
//        dataBinding.mainRecyclerView.layoutManager = LinearLayoutManager(this)
//
//        modelListItem = ArrayList()
//        prepareData()
//
//        val adapter = AdapterModelMainItem(modelListItem)
//        dataBinding.mainRecyclerView.adapter = adapter

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

//    private fun prepareData() {
//
//        val bestSellerList = ArrayList<RecyclerItem>()
//        bestSellerList.add(RecyclerItem(R.drawable.bags , "Up to 20% off"))
//        bestSellerList.add(RecyclerItem(R.drawable.mobiles, "Up to 10% off"))
//        bestSellerList.add(RecyclerItem(R.drawable.watches, "Up to 40% off"))
//        bestSellerList.add(RecyclerItem(R.drawable.bags, "Up to 20% off"))
//        bestSellerList.add(RecyclerItem(R.drawable.mobiles, "Up to 10% off"))
//        bestSellerList.add(RecyclerItem(R.drawable.watches, "Up to 40% off"))
//
//        val clothingList = ArrayList<RecyclerItem>()
//        clothingList.add(RecyclerItem(R.drawable.levis_clothing, "Up to 25% off"))
//        clothingList.add(RecyclerItem(R.drawable.women_clothing, "Up to 30% off"))
//        clothingList.add(RecyclerItem(R.drawable.nikeshoes, "Up to 35% off"))
//        clothingList.add(RecyclerItem(R.drawable.levis_clothing, "Up to 25% off"))
//        clothingList.add(RecyclerItem(R.drawable.women_clothing, "Up to 30% off"))
//        clothingList.add(RecyclerItem(R.drawable.nikeshoes, "Up to 35% off"))
//
//        modelListItem.add(DataModelItem(DataItemType.BEST_SELLER, bestSellerList))
//        modelListItem.add(DataModelItem(DataItemType.BANNER, Banner(R.drawable.tv_offer)))
//        modelListItem.add(DataModelItem(DataItemType.CLOTHING, clothingList))
//        modelListItem.add(DataModelItem(DataItemType.BANNER, Banner(R.drawable.nikon_canon_offer)))
//        modelListItem.add(DataModelItem(DataItemType.BEST_SELLER, bestSellerList.asReversed()))
//        modelListItem.add(DataModelItem(DataItemType.BANNER, Banner(R.drawable.offer_shoping)))
//    }
}