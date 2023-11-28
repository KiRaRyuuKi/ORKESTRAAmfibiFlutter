package com.orkestra.paycars.models.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.orkestra.paycars.R
import com.orkestra.paycars.controllers.adapter.AdapterDataContent
import com.orkestra.paycars.controllers.adapter.AdapterModelMainItem
import com.orkestra.paycars.controllers.model.ModelDataContent
import com.orkestra.paycars.controllers.model.ModelDataItem
import com.orkestra.paycars.controllers.view.ViewDataBanner
import com.orkestra.paycars.controllers.view.ViewDataItem
import com.orkestra.paycars.controllers.view.ViewDataContent
import com.orkestra.paycars.controllers.view.ViewDataListItem
import com.orkestra.paycars.databinding.FragmentHomeBinding
import com.orkestra.paycars.models.ui.MainContentActivity
import com.orkestra.paycars.services.ApiClientService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private lateinit var dataBinding: FragmentHomeBinding
    private lateinit var modelListItem: ArrayList<ModelDataItem>

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<ViewDataContent>
    private lateinit var adapterDataProduct: AdapterDataContent

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AccountFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun contentOnClickListener(product: ModelDataContent) {
        val detailIntent = Intent(requireContext(), MainContentActivity::class.java).apply {
            putExtra("thumbnail", product.thumbnail)
            putExtra("title", product.title)
            putExtra("description", product.description)
            putExtra("brand", product.brand)
            putExtra("price", product.price)
        }
        startActivity(detailIntent)
    }

    private fun getDataContent() {
        swipeRefreshLayout.isRefreshing = true

        val productService = ApiClientService.apiDataService
        call = productService.getAllProduct()

        call.enqueue(object : Callback<ViewDataContent> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ViewDataContent>, response: Response<ViewDataContent>) {
                swipeRefreshLayout.isRefreshing = false
                if (response.isSuccessful) {
                    adapterDataProduct.setData(response.body()?.products ?: emptyList())
                }
            }

            override fun onFailure(call: Call<ViewDataContent>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun prepareData() {

        val bestSellerList = ArrayList<ViewDataItem>()
        bestSellerList.add(ViewDataItem(R.drawable.profile , "Up to 20% off"))
        bestSellerList.add(ViewDataItem(R.drawable.profile, "Up to 10% off"))
        bestSellerList.add(ViewDataItem(R.drawable.profile, "Up to 40% off"))
        bestSellerList.add(ViewDataItem(R.drawable.profile, "Up to 20% off"))
        bestSellerList.add(ViewDataItem(R.drawable.profile, "Up to 10% off"))
        bestSellerList.add(ViewDataItem(R.drawable.profile, "Up to 40% off"))

        val clothingList = ArrayList<ViewDataItem>()
        clothingList.add(ViewDataItem(R.drawable.profile, "Up to 25% off"))
        clothingList.add(ViewDataItem(R.drawable.profile, "Up to 30% off"))
        clothingList.add(ViewDataItem(R.drawable.profile, "Up to 35% off"))
        clothingList.add(ViewDataItem(R.drawable.profile, "Up to 25% off"))
        clothingList.add(ViewDataItem(R.drawable.profile, "Up to 30% off"))
        clothingList.add(ViewDataItem(R.drawable.profile, "Up to 35% off"))

        modelListItem.apply {
            add(ModelDataItem(ViewDataListItem.BEST_SELLER, bestSellerList))
            add(ModelDataItem(ViewDataListItem.CARS, clothingList))
            add(ModelDataItem(ViewDataListItem.BANNER, ViewDataBanner(R.drawable.profile)))
            add(ModelDataItem(ViewDataListItem.CARS, clothingList))
        }
    }
}
