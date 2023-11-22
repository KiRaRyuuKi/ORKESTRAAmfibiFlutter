package com.orkestra.paycars.models.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.orkestra.paycars.R
import com.orkestra.paycars.controllers.adapter.AdapterDataContent
import com.orkestra.paycars.controllers.model.DataModelContent
import com.orkestra.paycars.controllers.model.view.ViewListContent
import com.orkestra.paycars.services.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<ViewListContent>
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

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshHome)
        recyclerView = view.findViewById(R.id.recyclerDataSearch)

        adapterDataProduct = AdapterDataContent { products -> productOnClickListener(products) }
        recyclerView.adapter = adapterDataProduct
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        swipeRefreshLayout.setOnRefreshListener {
            getDataProduct()
        }
        getDataProduct()

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

    private fun productOnClickListener(product: DataModelContent) {
        Toast.makeText(context, product.description, Toast.LENGTH_SHORT).show()
    }

    private fun getDataProduct() {
        swipeRefreshLayout.isRefreshing = true

        val productService = ApiClient.productService
        call = productService.getAllProduct()

        call.enqueue(object : Callback<ViewListContent> {
            override fun onResponse(call: Call<ViewListContent>, response: Response<ViewListContent>) {
                swipeRefreshLayout.isRefreshing = false
                if (response.isSuccessful) {
                    adapterDataProduct.submitList(response.body()?.products)
                }
            }

            override fun onFailure(call: Call<ViewListContent>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}