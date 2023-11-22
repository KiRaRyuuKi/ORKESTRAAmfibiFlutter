package com.orkestra.paycars.models.ui.menu

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.orkestra.paycars.R
import com.orkestra.paycars.databinding.ActivityMainHomeBinding
import com.orkestra.paycars.models.fragment.AccountFragment
import com.orkestra.paycars.models.fragment.HomeFragment
import com.orkestra.paycars.models.fragment.SearchFragment
import com.orkestra.paycars.models.fragment.TransactionFragment

class MainHomeActivity : AppCompatActivity() {
    private val ListAdapter : RecyclerView.Adapter<*>? = null
    private val RecyclerViewAdapter : RecyclerView? = null
    private lateinit var binding : ActivityMainHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_home)

        binding = ActivityMainHomeBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            if (item.getItemId() === R.id.homeItem) {
                replaceFragment(HomeFragment())
            } else if (item.getItemId() === R.id.accountItem) {
                replaceFragment(AccountFragment())
            } else if (item.getItemId() === R.id.transactionItem) {
                replaceFragment(TransactionFragment())
            } else if (item.getItemId() === R.id.searchItem) {
                replaceFragment(SearchFragment())
            }
            true
        }
        initRecyclerView()
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    private fun initRecyclerView() {}
}