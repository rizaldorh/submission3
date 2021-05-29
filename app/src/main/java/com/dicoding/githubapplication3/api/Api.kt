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
    @Headers("Authorization: token MASUKKAN TOKEN")
    fun getPencariPengguna(
        @Query("q") query: String
    ): Call<PenggunaRespons>

    @GET("users/{namapengguna}")
    @Headers("Authorization: token MASUKKAN TOKEN")
    fun getPenggunaDetail(
            @Path("namapengguna") namapengguna: String
    ): Call<DetailPenggunaRespons>

    @GET("users/{namapengguna}/followers")
    @Headers("Authorization: token MASUKKAN TOKEN")
    fun getPengikut(
            @Path("namapengguna") namapengguna: String
    ): Call<ArrayList<Pengguna>>

    @GET("users/{namapengguna}/following")
    @Headers("Authorization: token MASUKKAN TOKEN")
    fun getMengikuti(
            @Path("namapengguna") namapengguna: String
    ): Call<ArrayList<Pengguna>>
}