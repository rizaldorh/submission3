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

class PengikutViewModel : ViewModel() {
    val daftarPengikut = MutableLiveData<ArrayList<Pengguna>>()

    fun setDaftarPengikut(namapengguna: String) {
        RetrofitClient.apiInstance
                .getPengikut(namapengguna)
                .enqueue(object : Callback<ArrayList<Pengguna>> {
                    override fun onResponse(call: Call<ArrayList<Pengguna>>, response: Response<ArrayList<Pengguna>>) {
                        if (response.isSuccessful) {
                            daftarPengikut.postValue(response.body())
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<Pengguna>>, t: Throwable) {
                        Log.d("Failure", t.message!!)
                    }

                })
    }

    fun getDaftarPengikut(): LiveData<ArrayList<Pengguna>> {
        return daftarPengikut
    }
}