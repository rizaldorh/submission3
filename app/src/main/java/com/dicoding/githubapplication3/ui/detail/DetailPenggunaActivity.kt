package com.dicoding.githubapplication3.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.githubapplication3.databinding.ActivityDetailPenggunaBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailPenggunaActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DATAPENGGUNA = "extra_datapengguna"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_URL = "extra_url"
    }

    private lateinit var binding: ActivityDetailPenggunaBinding
    private lateinit var viewModel: DetailPenggunaViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailPenggunaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val namapengguna = intent.getStringExtra(EXTRA_DATAPENGGUNA)
        val id = intent.getIntExtra(EXTRA_ID, 0)
        val avatarUrl = intent.getStringExtra(EXTRA_URL)

        val paket = Bundle()
        paket.putString(EXTRA_DATAPENGGUNA, namapengguna)

        val sectionPagerAdapter = SectionPagerAdapter(this, supportFragmentManager, paket)
        binding.apply {
            viewPager.adapter = sectionPagerAdapter
            tabs.setupWithViewPager(viewPager)
        }

        var _isCek = false
        binding.toggleButtonFavorite.setOnClickListener {
            _isCek = !_isCek
            if (_isCek) {
                if (namapengguna != null) {
                    viewModel.tambahKeFavorite(namapengguna, id, avatarUrl!!)
                }
            } else {
                viewModel.hapusDariFavorite(id)
            }
            binding.toggleButtonFavorite.isChecked = _isCek
        }

        viewModel = ViewModelProvider(this).get(DetailPenggunaViewModel::class.java)

        viewModel.setPenggunaDetail(namapengguna!!)
        viewModel.getPenggunaDetail().observe(this, {
            if (it != null) {
                binding.apply {
                    textViewNama.text = it.name
                    textViewNamapengguna.text = it.login
                    textViewFollowers.text = "${it.followers} Pengikut"
                    textViewFollowing.text = "${it.following} Mengikuti"
                    Glide.with(this@DetailPenggunaActivity)
                        .load(it.avatar_url)
                        .transition(DrawableTransitionOptions.withCrossFade())
                        .into(imageViewProfil)
                }
            }
        })


        CoroutineScope(Dispatchers.IO).launch {
            val hitung = viewModel.cekPengguna(id)
            withContext(Dispatchers.Main) {
                if (hitung != null) {
                    if (hitung > 0) {
                        binding.toggleButtonFavorite.isChecked = true
                        _isCek = true
                    } else {
                        binding.toggleButtonFavorite.isChecked = false
                        _isCek = false
                    }
                }
            }
        }

    }
}

