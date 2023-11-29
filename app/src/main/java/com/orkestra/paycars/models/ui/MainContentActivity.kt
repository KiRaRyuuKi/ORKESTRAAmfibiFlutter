package com.orkestra.paycars.models.ui

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.orkestra.paycars.R
import com.orkestra.paycars.services.ApiClientService
import com.orkestra.paycars.services.ApiInterfaceService.Companion.PUBLISHABLE_KEY
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainContentActivity : AppCompatActivity() {

    private lateinit var paymentSheet: PaymentSheet
    private lateinit var customerId: String
    private lateinit var ephemeralKey: String
    private lateinit var clientSecret: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_content)

        val thumbnail: ImageView = findViewById(R.id.imageDetailContent)
        val title: TextView = findViewById(R.id.nameDetailContent)
        val description: TextView = findViewById(R.id.descriptionDetailContent)
        val price: TextView = findViewById(R.id.priceDetailContent)

        intent.extras?.apply {
            val thumbnailUrl = getString("thumbnail")
            Glide.with(this@MainContentActivity).load(thumbnailUrl).into(thumbnail)

            title.text = getString("title")
            description.text = getString("description")
            price.text = getString("price")
        }

        PaymentConfiguration.init(this, PUBLISHABLE_KEY)

        getCustomerId()
        val button = findViewById<Button>(R.id.buttonDetailContent)

        button.setOnClickListener {
            paymentFlow()
        }

        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)
    }

    private fun paymentFlow() {
        if (::clientSecret.isInitialized) {
            paymentSheet.presentWithPaymentIntent(
                clientSecret,
                PaymentSheet.Configuration(
                    "KiRa",
                    PaymentSheet.CustomerConfiguration(
                        customerId, ephemeralKey
                    )
                )
            )
        } else {
            Toast.makeText(this, "Client Secret is not initialized", Toast.LENGTH_SHORT).show()
        }
    }

    private var apiInterface = ApiClientService.getApiInterface()

    private fun getCustomerId() {
        lifecycleScope.launch(Dispatchers.IO) {
            val res = apiInterface.getCustomer()
            withContext(Dispatchers.Main) {
                if (res.isSuccessful && res.body()?.id != null) {
                    customerId = res.body()!!.id
                    getEphemeralKey(customerId)
                }
            }
        }
    }

    private fun getEphemeralKey(customerId: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val res = apiInterface.getEphemeralKey(customerId)
            withContext(Dispatchers.Main) {
                if (res.isSuccessful && res.body()?.id != null) {
                    ephemeralKey = res.body()!!.id
                    getPaymentIntent(customerId, ephemeralKey)
                }
            }
        }
    }

    private fun getPaymentIntent(customerId: String, ephemeralKey: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val res = apiInterface.getPaymentIntent(customerId)
            withContext(Dispatchers.Main) {
                if (res.isSuccessful && res.body()?.id != null) {
                    clientSecret = res.body()!!.id

                    Toast.makeText(this@MainContentActivity, "ORKESTRA Process Payment...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
        if (paymentSheetResult is PaymentSheetResult.Completed) {
            Toast.makeText(this, "Payment is Successful", Toast.LENGTH_SHORT).show()
        }
    }
}
