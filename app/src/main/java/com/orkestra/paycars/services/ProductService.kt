package com.orkestra.paycars.services

import com.orkestra.paycars.controllers.model.view.ViewListContent
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {

    @GET("products")
    fun getAllProduct(): Call<ViewListContent>
}