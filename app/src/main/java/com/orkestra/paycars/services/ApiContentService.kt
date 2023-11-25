package com.orkestra.paycars.services

import com.orkestra.paycars.controllers.view.ViewDataListContent
import com.orkestra.paycars.controllers.view.ViewDataUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiContentService {

    @POST("users/1")
    fun getDataUser(): Call<ViewDataUser>

    @GET("products")
    fun getAllProduct(): Call<ViewDataListContent>
}