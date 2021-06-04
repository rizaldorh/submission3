package com.dicoding.consumerapp

import android.net.Uri
import android.provider.BaseColumns

object KontrakDatabase {
    const val WEWENANG = "com.dicoding.githubapplication3"
    const val SKEMA = "content"

    internal class KolomFavoritPengguna : BaseColumns {
        companion object {
            const val NAMA_TABLE = "favorite_pengguna"
            const val ID = "id"
            const val NAMA_PENGGUNA = "login"
            const val AVATAR_URL = "avatar_url"

            val KONTEN_URI = Uri.Builder().scheme(SKEMA)
                .authority(WEWENANG)
                .appendPath(NAMA_TABLE)
                .build()
        }
    }
}