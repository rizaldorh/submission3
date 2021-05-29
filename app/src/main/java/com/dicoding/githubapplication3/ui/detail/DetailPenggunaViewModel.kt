package com.dicoding.githubapplication3.ui.detail

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.githubapplication3.api.RetrofitClient
import com.dicoding.githubapplication3.data.local.FavoritePengguna
import com.dicoding.githubapplication3.data.local.FavoritePenggunaDao
import com.dicoding.githubapplication3.data.local.PenggunaDatabase
import com.dicoding.githubapplication3.data.model.DetailPenggunaRespons
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailPenggunaViewModel(application: Application) : AndroidViewModel(application) {
    val user = MutableLiveData<DetailPenggunaRespons>()

    private var penggunaDao: FavoritePenggunaDao?
    private var penggunaDatabase: PenggunaDatabase?

    init {
        penggunaDatabase = PenggunaDatabase.getDatabase(application)
        penggunaDao = penggunaDatabase?.favoritePenggunaDao()
    }

    fun setPenggunaDetail(datapengguna: String) {
        RetrofitClient.apiInstance
            .getPenggunaDetail(datapengguna)
            .enqueue(object : Callback<DetailPenggunaRespons> {
                override fun onResponse(
                    call: Call<DetailPenggunaRespons>,
                    response: Response<DetailPenggunaRespons>
                ) {
                    if (response.isSuccessful) {
                        user.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<DetailPenggunaRespons>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }

            })
    }

    fun getPenggunaDetail(): LiveData<DetailPenggunaRespons> {
        return user
    }

    fun tambahKeFavorite(datapengguna: String, id:Int, avatarUrl: String){
        CoroutineScope(Dispatchers.IO).launch {
            var pengguna = FavoritePengguna(
                datapengguna,
                id,
                avatarUrl
            )
            penggunaDao?.tambahKeFavorite(pengguna)
        }
        }

    suspend fun cekPengguna(id:Int) = penggunaDao?.cekPengguna(id)

    fun hapusDariFavorite(id: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            penggunaDao?.hapusDariFavorite(id)
        }
    }
    }
