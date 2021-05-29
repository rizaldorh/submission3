package com.dicoding.githubapplication3.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.githubapplication3.api.RetrofitClient
import com.dicoding.githubapplication3.data.model.Pengguna
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MengikutiViewModel : ViewModel() {
    val daftarMengikuti = MutableLiveData<ArrayList<Pengguna>>()

    fun setDaftarMengikuti(namapengguna: String) {
        RetrofitClient.apiInstance
                .getMengikuti(namapengguna)
                .enqueue(object : Callback<ArrayList<Pengguna>> {
                    override fun onResponse(call: Call<ArrayList<Pengguna>>, response: Response<ArrayList<Pengguna>>) {
                        if (response.isSuccessful) {
                            daftarMengikuti.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<Pengguna>>, t: Throwable) {
                        Log.d("Failure", t.message!!)
                    }

                })
    }

    fun getDaftarMengikuti(): LiveData<ArrayList<Pengguna>> {
        return daftarMengikuti
    }
}