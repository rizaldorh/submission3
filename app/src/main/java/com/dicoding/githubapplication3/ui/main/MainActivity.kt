package com.dicoding.githubapplication3.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubapplication3.R
import com.dicoding.githubapplication3.data.model.Pengguna
import com.dicoding.githubapplication3.databinding.ActivityMainBinding
import com.dicoding.githubapplication3.ui.detail.DetailPenggunaActivity
import com.dicoding.githubapplication3.ui.favorite.FavoriteActivity
import com.dicoding.githubapplication3.ui.pengaturan.PengaturanActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PenggunaViewModel
    private lateinit var adapter: PenggunaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PenggunaAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemTekanHubungikembali(object : PenggunaAdapter.OnItemTekanHubungikembali {
            override fun onItemDitekan(data: Pengguna) {
                Intent(this@MainActivity, DetailPenggunaActivity::class.java).also {
                    it.putExtra(DetailPenggunaActivity.EXTRA_DATAPENGGUNA, data.login)
                    it.putExtra(DetailPenggunaActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailPenggunaActivity.EXTRA_URL, data.avatar_url)
                    startActivity(it)
                }
            }

        })
        viewModel = ViewModelProvider(this, NewInstanceFactory()).get(PenggunaViewModel::class.java)

        binding.apply {
            recyclerViewPengguna.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewPengguna.setHasFixedSize(true)
            recyclerViewPengguna.adapter = adapter

            tombolPencari.setOnClickListener {
                cariPengguna()
            }

            editTextQuery.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    //COMMAND
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        viewModel.getPencariPengguna().observe(this, {
            if (it != null) {
                adapter.setDaftar(it)
                tampilMemuat(false)
            }
        })

    }

    private fun cariPengguna() {
        binding.apply {
            val query = editTextQuery.text.toString()
            if (query.isEmpty()) return
            tampilMemuat(true)
            viewModel.setPencariPengguna(query)
        }
    }

    private fun tampilMemuat(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.pengaturan_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.favorite_menu -> {
                Intent(this, FavoriteActivity::class.java).also {
                    startActivity(it)
                }
            }

            R.id.pengaturan_menu -> {
                Intent(this, PengaturanActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}


