package com.dicoding.githubapplication3.ui.favorite

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubapplication3.data.local.FavoritePengguna
import com.dicoding.githubapplication3.data.model.Pengguna
import com.dicoding.githubapplication3.databinding.ActivityFavoriteBinding
import com.dicoding.githubapplication3.ui.detail.DetailPenggunaActivity
import com.dicoding.githubapplication3.ui.main.PenggunaAdapter

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding : ActivityFavoriteBinding
    private lateinit var adapter: PenggunaAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PenggunaAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        adapter.setOnItemTekanHubungikembali(object : PenggunaAdapter.OnItemTekanHubungikembali {
            override fun onItemDitekan(data: Pengguna) {
                Intent(this@FavoriteActivity, DetailPenggunaActivity::class.java).also {
                    it.putExtra(DetailPenggunaActivity.EXTRA_DATAPENGGUNA, data.login)
                    it.putExtra(DetailPenggunaActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailPenggunaActivity.EXTRA_URL, data.avatar_url)
                    startActivity(it)
                }
            }

        })

        binding.apply {
            recyclerViewFavorite.setHasFixedSize(true)
            recyclerViewFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            recyclerViewFavorite.adapter = adapter
        }

        viewModel.getFavoritePengguna()?.observe(this, {
            if (it!=null){
                val daftar = mapDaftar(it)
                adapter.setDaftar(daftar)
            }
        })
    }

    private fun mapDaftar(pengguna: List<FavoritePengguna>): ArrayList<Pengguna> {
        val daftarPengguna_ = ArrayList<Pengguna>()
        for (pengguna_ in pengguna){
            val penggunaMapped = Pengguna(
                pengguna_.login,
                pengguna_.id,
                pengguna_.avatar_url
            )
            daftarPengguna_.add(penggunaMapped)
        }
        return daftarPengguna_
    }
}