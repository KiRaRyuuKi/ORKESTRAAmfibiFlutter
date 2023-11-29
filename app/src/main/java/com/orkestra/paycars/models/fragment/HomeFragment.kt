package com.orkestra.paycars.models.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
//import androidx.fragment.app.viewModels
//import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.load.engine.Resource
import com.orkestra.paycars.controllers.adapter.AdapterDataHome
//import com.orkestra.paycars.controllers.data.HomeViewModel
import com.orkestra.paycars.databinding.FragmentHomeBinding
import com.orkestra.paycars.models.ui.MainMessageActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
    private lateinit var dataBinding: FragmentHomeBinding
//    private val viewModel by viewModels<HomeViewModel>()
//    private val adapterDataHome = AdapterDataHome()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

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

        swipeRefreshLayout = dataBinding.swipeRefreshHome

//        dataBinding.recyclerView.apply {
//            setHasFixedSize(true)
//            layoutManager = LinearLayoutManager(requireContext())
//            adapter = adapterDataHome
//        }
//
//        viewModel.homeListItemsLiveData.observe(viewLifecycleOwner) { result ->
//            when (result) {
//                is Resource.Failure -> {
//                    dataBinding.swipeRefreshHome.isRefreshing = false
//                }
//                Resource.Loading -> {
//                    dataBinding.swipeRefreshHome.isRefreshing = true
//                }
//                is Resource.Success -> {
//                    dataBinding.swipeRefreshHome.isRefreshing = false
//                    adapterDataHome.items = result.value
//                }
//            }
//        }

        val openMessage = dataBinding.buttonMessageHome

        openMessage.setOnClickListener {
            val intent = Intent(requireContext(), MainMessageActivity::class.java)
            startActivity(intent)
        }
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
}
