package com.dicoding.githubapplication3.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorite_pengguna")
data class FavoritePengguna(
    val login: String,
    @PrimaryKey
    val id: Int,
    val avatar_url : String
): Serializable
