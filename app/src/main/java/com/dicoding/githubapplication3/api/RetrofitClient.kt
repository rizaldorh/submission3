package com.dicoding.githubapplication3.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val URL_DASAR = "https://api.github.com/"

    val retrofit = Retrofit.Builder()
        .baseUrl(URL_DASAR)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiInstance = retrofit.create(Api::class.java)
}
