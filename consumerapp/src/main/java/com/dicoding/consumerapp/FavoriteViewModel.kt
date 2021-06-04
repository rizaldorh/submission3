package com.dicoding.consumerapp

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {

    private var daftar = MutableLiveData<ArrayList<Pengguna>>()

    fun setFavoritPengguna(context: Context) {
        val cursor = context.contentResolver.query(
            KontrakDatabase.KolomFavoritPengguna.KONTEN_URI,
            null,
            null,
            null,
            null
        )
        val daftarTerkonversi = PembantuMapping.mapPengubahKursorMenjadiArrayList(cursor)
        daftar.postValue(daftarTerkonversi)
    }

    fun getFavoritePengguna(): LiveData<ArrayList<Pengguna>> {
        return daftar
    }
}