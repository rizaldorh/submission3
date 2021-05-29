package com.dicoding.githubapplication3.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.githubapplication3.R
import com.dicoding.githubapplication3.databinding.FragmentFollowBinding
import com.dicoding.githubapplication3.ui.main.PenggunaAdapter

class MengikutiFragment : Fragment(R.layout.fragment_follow) {

    private var binding_: FragmentFollowBinding? = null
    private val binding get() = binding_!!
    private lateinit var viewModel: MengikutiViewModel
    private lateinit var adapter: PenggunaAdapter
    private lateinit var namapengguna: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments
        namapengguna = args?.getString(DetailPenggunaActivity.EXTRA_DATAPENGGUNA).toString()

        binding_ = FragmentFollowBinding.bind(view)

        adapter = PenggunaAdapter()
        adapter.notifyDataSetChanged()

        binding.apply {
            recyclerViewPengikut.setHasFixedSize(true)
            recyclerViewPengikut.layoutManager = LinearLayoutManager(activity)
            recyclerViewPengikut.adapter = adapter
        }
        tampilMemuat(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(MengikutiViewModel::class.java)
        viewModel.setDaftarMengikuti(namapengguna)
        viewModel.getDaftarMengikuti().observe(viewLifecycleOwner, {
            if (it != null) {
                adapter.setDaftar(it)
                tampilMemuat(false)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding_ = null
    }

    private fun tampilMemuat(state: Boolean) {
        if (state) {
            binding.progressBarPengikut.visibility = View.VISIBLE
        } else {
            binding.progressBarPengikut.visibility = View.GONE
        }
    }
}