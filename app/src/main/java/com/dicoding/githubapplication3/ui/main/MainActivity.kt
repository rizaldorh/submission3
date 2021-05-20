package com.dicoding.githubapplication3.ui.main

import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubapplication3.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel : MainViewModel
    private lateinit var adapter : PenggunaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PenggunaAdapter()
        adapter.notifyDataSetChanged()
        viewModel = ViewModelProvider(this, NewInstanceFactory()).get(MainViewModel::class.java)

        binding.apply {
            recyclerViewPengguna.layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewPengguna.setHasFixedSize(true)
            recyclerViewPengguna.adapter = adapter

            tombolPencari.setOnClickListener{
                cariPengguna()
            }

            editTextQuery.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER){
                    //COMMAND
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        viewModel.getPencariPengguna().observe(this, {
            if (it!=null) {
            adapter.setDaftar(it)
            tampilMemuat(false)
        }
        })

    }

    private fun cariPengguna(){
        binding.apply {
            val query =editTextQuery.text.toString()
            if (query.isEmpty()) return
            tampilMemuat(true)
            viewModel.setPencariPengguna(query)
        }
    }

    private fun tampilMemuat(state: Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}


