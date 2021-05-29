package com.dicoding.githubapplication3.ui.detail

import android.content.Context
import android.os.Bundle
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.dicoding.githubapplication3.R

class SectionPagerAdapter(private val mContext: Context, fragmentmanager: FragmentManager, data: Bundle) : FragmentPagerAdapter(fragmentmanager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var fragmentPaket: Bundle

    init {
        fragmentPaket = data
    }

    @StringRes
    private val JUDUL_TAB = intArrayOf(R.string.tab_1, R.string.tab_2)

    override fun getCount(): Int = 2

    override fun getItem(position: Int): Fragment {
        var fragmentdata: Fragment? = null
        when(position){
            0 -> fragmentdata = PengikutFragment()
            1 -> fragmentdata = MengikutiFragment()
        }
        fragmentdata?.arguments = this.fragmentPaket
        return fragmentdata as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mContext.resources.getString(JUDUL_TAB[position])
    }
}