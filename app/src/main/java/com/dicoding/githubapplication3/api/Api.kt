package com.dicoding.githubapplication3.api

import com.dicoding.githubapplication3.data.model.DetailPenggunaRespons
import com.dicoding.githubapplication3.data.model.Pengguna
import com.dicoding.githubapplication3.data.model.PenggunaRespons
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getPencariPengguna(
        @Query("q") query: String
    ): Call<PenggunaRespons>

    @GET("users/{namapengguna}")
    @Headers("Authorization: token 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")
    fun getPenggunaDetail(
        @Path("namapengguna") namapengguna: String
    ): Call<DetailPenggunaRespons>

    @GET("users/{namapengguna}/following")
    @Headers("Authorization: token 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")
    fun getMengikuti(
        @Path("namapengguna") namapengguna: String
    ): Call<ArrayList<Pengguna>>

    @GET("users/{namapengguna}/followers")
    @Headers("Authorization: token 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")
    fun getPengikut(
        @Path("namapengguna") namapengguna: String
    ): Call<ArrayList<Pengguna>>
}