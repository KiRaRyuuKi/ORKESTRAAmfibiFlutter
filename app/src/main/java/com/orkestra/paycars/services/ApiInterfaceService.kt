package com.orkestra.paycars.services

import com.orkestra.paycars.controllers.view.ViewDataClient
import com.orkestra.paycars.controllers.view.ViewDataCustomer
import retrofit2.Response
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiInterfaceService {

        companion object {
                const val PUBLISHABLE_KEY = "pk_test_51OFYSpLz8qBEbQZ9aO0fDbRvspdUUv9aaoiU6nCkqO8ANl6Ez7FcGP6GvjwmwL0NTiBmIqaIq9pBDoWzMQBBqK2400OwrJRCMK"
                const val SECRET_KEY = "sk_test_51OFYSpLz8qBEbQZ9grK14vqvUPvTefXFno6qShpWgeAKxjzoz0C1QIcdxMiJrG0eTa3DFAPyfDeornA2wlsVptjZ00AK3EC7Ml"
        }

        @Headers("Authorization: Bearer $SECRET_KEY")
        @POST("v1/customers")
        suspend fun getCustomer(): Response<ViewDataCustomer>

        @Headers(
                "Authorization: Bearer $SECRET_KEY",
                "Stripe-Version: 2023-10-16")
        @POST("v1/ephemeral_keys")
        suspend fun getEphemeralKey(
                @Query("customer") customer: String
        ): Response<ViewDataCustomer>

        @Headers("Authorization: Bearer $SECRET_KEY")
        @POST("v1/payment_intents")
        suspend fun getPaymentIntent(
                @Query("customer") customer: String,
                @Query("amount") amount: String = "100",
                @Query("currency") currency: String="idr",
                @Query("automatic_payment_methods [enabled]") automatePay: Boolean = true,
        ): Response<ViewDataClient>
}