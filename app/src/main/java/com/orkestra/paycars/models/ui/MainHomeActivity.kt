package com.orkestra.paycars.models.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.orkestra.paycars.R
import com.orkestra.paycars.databinding.ActivityMainHomeBinding
import com.orkestra.paycars.models.fragment.AccountFragment
import com.orkestra.paycars.models.fragment.HomeFragment
import com.orkestra.paycars.models.fragment.SearchFragment
import com.orkestra.paycars.models.fragment.TransactionFragment

@Suppress("DEPRECATED_IDENTITY_EQUALS")
class MainHomeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainHomeBinding
    private var dataLoaded = false
    private var dataLoading = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_home)

        binding = ActivityMainHomeBinding.inflate(layoutInflater)
        setContentView(binding.getRoot())
        replaceFragment(HomeFragment())

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

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val currentFragment = supportFragmentManager.findFragmentById(R.id.frameLayout)

        if (currentFragment is SearchFragment) {
            handleBackPressedFragment()
        } else {
            super.onBackPressed()
        }
    }

    private fun handleBackPressedFragment() {
        if (!dataLoading) {
            if (dataLoaded) {
                super.onBackPressed()
            } else {
                dataLoading = true
                Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show()

                Handler(Looper.getMainLooper()).postDelayed({
                    dataLoaded = true
                    dataLoading = false
                }, 2000)
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }

    private fun initRecyclerView() {}
}