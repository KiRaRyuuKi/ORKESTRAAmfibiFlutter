package com.orkestra.paycars.models.ui.content

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.orkestra.paycars.R
import com.orkestra.paycars.services.ApiUtilities
import com.orkestra.paycars.services.UtilitiesAccess.PUBLISHABLE_KEY
import com.stripe.android.PaymentConfiguration
import com.stripe.android.paymentsheet.PaymentSheet
import com.stripe.android.paymentsheet.PaymentSheetResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainContentActivity : AppCompatActivity() {

    lateinit var paymentSheet: PaymentSheet
    lateinit var customerId: String
    lateinit var ephemeralKey: String
    lateinit var clientSecret: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_content)

        PaymentConfiguration.init(this, PUBLISHABLE_KEY)

        getCustomerId()
        val button = findViewById<Button>(R.id.buttonPayment)

        button.setOnClickListener {
            paymentFlow()
        }

        paymentSheet = PaymentSheet(this, ::onPaymentSheetResult)
    }

    private fun paymentFlow() {
        clientSecret
        PaymentSheet.Configuration(
            "KiRa",
            PaymentSheet.CustomerConfiguration(
                customerId, ephemeralKey
            )
        )
    }

    private var apiInterface = ApiUtilities.getApiInterface()

    private fun getCustomerId() {
        lifecycleScope.launch(Dispatchers.IO) {
            val res = apiInterface.getCustomer()
            withContext(Dispatchers.Main) {
                if(res.isSuccessful && res.body()!=null) {
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
                if(res.isSuccessful && res.body()!=null) {
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
                if(res.isSuccessful && res.body()!=null) {
                    clientSecret = res.body()!!.id

                    Toast.makeText(this@MainContentActivity, "ORKESTRA Process Payment...", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun onPaymentSheetResult(paymentSheetResult: PaymentSheetResult) {
        if(paymentSheetResult is PaymentSheetResult.Completed) {
            Toast.makeText(this, "Payment is Successful", Toast.LENGTH_SHORT).show()
        }
    }
}