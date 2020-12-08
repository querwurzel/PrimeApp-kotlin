package com.wilke.android.prime.api

import com.wilke.android.prime.api.model.PrimeMeta
import retrofit2.Call
import retrofit2.http.GET

interface PrimeClient {

    @GET("/results")
    fun fetchResults(): Call<String>

    @GET("/results/meta")
    fun fetchMeta(): Call<String>

}
