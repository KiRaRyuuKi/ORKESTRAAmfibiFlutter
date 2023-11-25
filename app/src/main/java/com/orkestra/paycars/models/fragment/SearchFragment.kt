package com.orkestra.paycars.models.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.orkestra.paycars.R
import com.orkestra.paycars.controllers.adapter.AdapterDataContent
import com.orkestra.paycars.services.ApiClientService
import com.orkestra.paycars.controllers.model.ModelDataContent
import com.orkestra.paycars.controllers.view.ViewDataListContent
import com.orkestra.paycars.models.ui.MainContentActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SearchFragment : Fragment() {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var call: Call<ViewDataListContent>
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
        val view = inflater.inflate(R.layout.fragment_search, container, false)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshSearch)
        recyclerView = view.findViewById(R.id.recyclerDataSearch)
        searchView = view.findViewById(R.id.searchView)

        adapterDataProduct = AdapterDataContent { product -> productOnClickListener(product) }
        recyclerView.adapter = adapterDataProduct
        recyclerView.layoutManager = GridLayoutManager(context, 2)

        swipeRefreshLayout.setOnRefreshListener {
            getDataProduct()
        }
        setupSearchView()
        getDataProduct()

        return view
    }


    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            SearchFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun productOnClickListener(product: ModelDataContent) {
        val detailIntent = Intent(requireContext(), MainContentActivity::class.java).apply {
            putExtra("thumbnail", product.thumbnail)
            putExtra("title", product.title)
            putExtra("description", product.description)
            putExtra("brand", product.brand)
            putExtra("price", product.price)
        }
        startActivity(detailIntent)
    }

    private fun setupSearchView() {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapterDataProduct.searchDataContent(newText.orEmpty())
                return true
            }
        })
    }

    private fun getDataProduct() {
        swipeRefreshLayout.isRefreshing = true

        val productService = ApiClientService.apiContentService
        call = productService.getAllProduct()

        call.enqueue(object : Callback<ViewDataListContent> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ViewDataListContent>, response: Response<ViewDataListContent>) {
                swipeRefreshLayout.isRefreshing = false
                if (response.isSuccessful) {
                    adapterDataProduct.setData(response.body()?.products ?: emptyList())
                }
            }

            override fun onFailure(call: Call<ViewDataListContent>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
