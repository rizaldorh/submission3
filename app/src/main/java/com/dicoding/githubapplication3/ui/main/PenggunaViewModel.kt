package com.dicoding.githubapplication3.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubapplication3.api.RetrofitClient
import com.dicoding.githubapplication3.data.model.Pengguna
import com.dicoding.githubapplication3.data.model.PenggunaRespons
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PenggunaViewModel : ViewModel() {
    val daftarPengguna = MutableLiveData<ArrayList<Pengguna>>()

    fun setPencariPengguna(query: String){
        RetrofitClient.apiInstance
            .getPencariPengguna(query)
            .enqueue(object : Callback<PenggunaRespons>{
                override fun onResponse(
                    call: Call<PenggunaRespons>,
                    response: Response<PenggunaRespons>
                ) {
                    if (response.isSuccessful) {
                        daftarPengguna.postValue((response.body()?.items))
                    }
                }

                override fun onFailure(call: Call<PenggunaRespons>, t: Throwable) {
                    Log.d("Failure", t.message!!)
                }
            })
    }

    fun getPencariPengguna(): LiveData<ArrayList<Pengguna>>{
        return daftarPengguna
    }
}