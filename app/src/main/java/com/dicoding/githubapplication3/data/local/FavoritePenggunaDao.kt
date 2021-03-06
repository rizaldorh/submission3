package com.dicoding.githubapplication3.data.local

import android.database.Cursor
import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface FavoritePenggunaDao {
    @Insert
    suspend fun tambahKeFavorite(favoritePengguna: FavoritePengguna)

    @Query("SELECT * FROM favorite_pengguna")
    fun getFavoritePengguna(): LiveData<List<FavoritePengguna>>

    @Query("SELECT count(*) FROM favorite_pengguna WHERE favorite_pengguna.id = :id")
    suspend fun cekPengguna(id: Int): Int

    @Query("DELETE FROM favorite_pengguna WHERE favorite_pengguna.id = :id")
    suspend fun hapusDariFavorite(id: Int): Int

    @Query("SELECT * FROM favorite_pengguna")
    fun cariSemua(): Cursor

}