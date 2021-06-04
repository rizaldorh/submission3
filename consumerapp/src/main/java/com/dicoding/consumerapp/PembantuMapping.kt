package com.dicoding.consumerapp

import android.database.Cursor

object PembantuMapping {
    fun mapPengubahKursorMenjadiArrayList(cursor: Cursor?): ArrayList<Pengguna> {
        val daftar = ArrayList<Pengguna>()
        if (cursor != null) {
            while (cursor.moveToNext()) {
                val id =
                    cursor.getInt(cursor.getColumnIndexOrThrow(KontrakDatabase.KolomFavoritPengguna.ID))
                val namaPengguna =
                    cursor.getString(cursor.getColumnIndexOrThrow(KontrakDatabase.KolomFavoritPengguna.NAMA_PENGGUNA))
                val avatarUrl =
                    cursor.getString(cursor.getColumnIndexOrThrow(KontrakDatabase.KolomFavoritPengguna.AVATAR_URL))
                daftar.add(
                    Pengguna(
                        namaPengguna,
                        id,
                        avatarUrl
                    )
                )
            }
        }
        return daftar
    }
}