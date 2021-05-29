package com.dicoding.githubapplication3.ui.favorite

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dicoding.githubapplication3.data.local.FavoritePengguna
import com.dicoding.githubapplication3.data.local.FavoritePenggunaDao
import com.dicoding.githubapplication3.data.local.PenggunaDatabase

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private var penggunaDao: FavoritePenggunaDao?
    private var penggunaDatabase: PenggunaDatabase?

    init {
        penggunaDatabase = PenggunaDatabase.getDatabase(application)
        penggunaDao = penggunaDatabase?.favoritePenggunaDao()
    }

    fun getFavoritePengguna(): LiveData<List<FavoritePengguna>>? {
        return penggunaDao?.getFavoritePengguna()
    }
}