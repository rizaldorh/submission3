package com.dicoding.githubapplication3.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.dicoding.githubapplication3.data.local.FavoritePenggunaDao
import com.dicoding.githubapplication3.data.local.PenggunaDatabase

class PenggunaContentProvider : ContentProvider() {

    companion object {
        const val WEWENANG = "com.dicoding.githubapplication3"
        const val NAMA_TABEL = "favorite_pengguna"
        const val ID_DATA_PENGGUNA_FAVORIT = 1
        val pencocokUri = UriMatcher(UriMatcher.NO_MATCH)

        init {
            pencocokUri.addURI(WEWENANG, NAMA_TABEL, ID_DATA_PENGGUNA_FAVORIT)
        }
    }

    private lateinit var penggunaDao: FavoritePenggunaDao

    override fun onCreate(): Boolean {
        penggunaDao = context?.let { context ->
            PenggunaDatabase.getDatabase(context)?.favoritePenggunaDao()
        }!!
        return false
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        var kursor: Cursor?
        when (pencocokUri.match(uri)) {
            ID_DATA_PENGGUNA_FAVORIT -> {
                kursor = penggunaDao.cariSemua()
                if (context != null) {
                    kursor.setNotificationUri(context?.contentResolver, uri)
                }
            }
            else -> {
                kursor = null
            }
        }
        return kursor
    }

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        return 0
    }

    override fun getType(uri: Uri): String? {
        return null
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        return null
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        return 0
    }
}