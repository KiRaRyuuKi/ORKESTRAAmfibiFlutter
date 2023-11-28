package com.orkestra.paycars.services

import com.orkestra.paycars.controllers.view.ViewDataContent
import com.orkestra.paycars.controllers.view.ViewDataTransaction
import com.orkestra.paycars.controllers.view.ViewDataUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiDataService {

    @POST("users/1")
    fun getDataUser(): Call<ViewDataUser>

    @GET("products")
    fun getAllProduct(): Call<ViewDataContent>

    @GET("products")
    fun getAllTransaction(): Call<ViewDataTransaction>
}