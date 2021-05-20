package com.dicoding.githubapplication3.api

import com.dicoding.githubapplication3.data.model.PenggunaRespons
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getPencariPengguna(
        @Query("q") query: String
    ): Call<PenggunaRespons>
}