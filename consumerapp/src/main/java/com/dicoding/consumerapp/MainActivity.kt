package com.dicoding.consumerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.consumerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: PenggunaAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PenggunaAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        binding.apply {
            recyclerViewFavorite.setHasFixedSize(true)
            recyclerViewFavorite.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewFavorite.adapter = adapter
        }

        viewModel.setFavoritPengguna(this)

        viewModel.getFavoritePengguna().observe(this, {
            if (it != null) {
                adapter.setDaftar(it)
            }
        })
    }

}