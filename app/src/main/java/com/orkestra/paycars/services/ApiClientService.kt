package com.orkestra.paycars.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClientService {

    private const val BASE_URL = "https://dummyjson.com/"
    private const val STRIPE_URL = "https://api.stripe.com/"

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiContentService: ApiContentService = retrofit.create(ApiContentService::class.java)

    fun getApiInterface(): ApiInterfaceService {
        return Retrofit.Builder()
            .baseUrl(STRIPE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiInterfaceService::class.java)
    }
}
