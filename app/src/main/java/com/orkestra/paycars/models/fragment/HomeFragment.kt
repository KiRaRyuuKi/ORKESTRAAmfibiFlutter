package com.orkestra.paycars.models.fragment

<<<<<<< Updated upstream
import android.annotation.SuppressLint
=======
import android.content.Context
import android.content.Intent
>>>>>>> Stashed changes
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< Updated upstream
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.orkestra.paycars.R
import com.orkestra.paycars.controllers.adapter.AdapterDataContent
import com.orkestra.paycars.controllers.model.DataModelContent
import com.orkestra.paycars.controllers.model.view.ViewListContent
import com.orkestra.paycars.services.ApiClient
=======
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.bumptech.glide.Glide
import com.orkestra.paycars.R
import com.orkestra.paycars.controllers.adapter.AdapterDataContent
import com.orkestra.paycars.controllers.model.ModelDataContent
import com.orkestra.paycars.controllers.model.ModelDataItem
import com.orkestra.paycars.controllers.view.ViewDataBanner
import com.orkestra.paycars.controllers.view.ViewDataContent
import com.orkestra.paycars.controllers.view.ViewDataItem
import com.orkestra.paycars.controllers.view.ViewDataListItem
import com.orkestra.paycars.databinding.FragmentHomeBinding
import com.orkestra.paycars.models.ui.MainContentActivity
>>>>>>> Stashed changes
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {
<<<<<<< Updated upstream
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<ViewListContent>
    private lateinit var adapterDataProduct: AdapterDataContent
=======

    private lateinit var dataBinding: FragmentHomeBinding
    private lateinit var modelListItem: ArrayList<ModelDataItem>

    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var call: Call<ViewDataContent>
    private lateinit var adapterDataProduct: AdapterDataContent
    private lateinit var tvUserName: TextView
    private lateinit var tvUserEmail: TextView
    private lateinit var userImageView: ImageView
>>>>>>> Stashed changes

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
<<<<<<< Updated upstream
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
=======
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        tvUserName = rootView.findViewById(R.id.nameHome)
        tvUserEmail = rootView.findViewById(R.id.usernameHome)
        userImageView = rootView.findViewById(R.id.imageHome)

        val preferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val userName = preferences.getString("username", "")
        val userEmail = preferences.getString("useremail", "")
        val userImageUrl = preferences.getString("userPhoto", "")

        tvUserName.text = userName
        tvUserEmail.text = userEmail
        Glide.with(this).load(userImageUrl).into(userImageView)

        dataBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // TODO: Implement your onViewCreated logic here
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
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
=======
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
            override fun onResponse(call: Call<ViewDataContent>, response: Response<ViewDataContent>) {
                swipeRefreshLayout.isRefreshing = false
                if (response.isSuccessful) {
                    adapterDataProduct.setData(response.body()?.products ?: emptyList())
                }
            }

            override fun onFailure(call: Call<ViewDataContent>, t: Throwable) {
>>>>>>> Stashed changes
                swipeRefreshLayout.isRefreshing = false
                Toast.makeText(context, t.localizedMessage, Toast.LENGTH_SHORT).show()
            }
        })
    }
<<<<<<< Updated upstream
}
=======

    private fun prepareData() {

        val bestSellerList = ArrayList<ViewDataItem>()
        bestSellerList.add(ViewDataItem(R.drawable.profile, "Up to 20% off"))
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
>>>>>>> Stashed changes
