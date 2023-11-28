package com.orkestra.paycars.models.fragment

import android.annotation.SuppressLint
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
import com.orkestra.paycars.controllers.adapter.AdapterDataTransaction
import com.orkestra.paycars.controllers.model.ModelDataTransaction
import com.orkestra.paycars.controllers.view.ViewDataTransaction
import com.orkestra.paycars.services.ApiClientService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TransactionFragment : Fragment() {
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private lateinit var call: Call<ViewDataTransaction>
    private lateinit var adapterDataTransaction: AdapterDataTransaction

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
        val view = inflater.inflate(R.layout.fragment_transaction, container, false)
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshTransaction)
        recyclerView = view.findViewById(R.id.recyclerDataTransaction)

        adapterDataTransaction = AdapterDataTransaction { product -> transactionOnClickListener(product) }
        recyclerView.adapter = adapterDataTransaction
        recyclerView.layoutManager = LinearLayoutManager(context)

        swipeRefreshLayout.setOnRefreshListener {
            getDataProduct()
        }
        getDataProduct()

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TransactionFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun transactionOnClickListener(product: ModelDataTransaction) {
        Toast.makeText(context, "Transaction is done", Toast.LENGTH_SHORT).show()
    }

    private fun getDataProduct() {
        swipeRefreshLayout.isRefreshing = true

        val transactionService = ApiClientService.apiTransactionService
        call = transactionService.getAllTransaction()

        call.enqueue(object : Callback<ViewDataTransaction> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<ViewDataTransaction>, response: Response<ViewDataTransaction>) {
                swipeRefreshLayout.isRefreshing = false
                if (response.isSuccessful) {
                    adapterDataTransaction.setData(response.body()?.products ?: emptyList())
                }
            }

            override fun onFailure(call: Call<ViewDataTransaction>, t: Throwable) {
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
}