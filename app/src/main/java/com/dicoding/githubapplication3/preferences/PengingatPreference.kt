package com.dicoding.githubapplication3.preferences

import android.content.Context
import com.dicoding.githubapplication3.data.model.Pengingat

class PengingatPreference(ctxt: Context) {
    companion object {
        const val PREFS_NAMA = "pengingat_pref"
        private const val PENGINGAT = "isPengingat"
    }

    private val preference = ctxt.getSharedPreferences(PREFS_NAMA, Context.MODE_PRIVATE)

    fun setPengingat(value: Pengingat) {
        val pengedit = preference.edit()
        pengedit.putBoolean(PENGINGAT, value.isTeringat)
        pengedit.apply()
    }

    fun getPengingat(): Pengingat {
        val pengingat_ = Pengingat()
        pengingat_.isTeringat = preference.getBoolean(PENGINGAT, false)
        return pengingat_
    }
}