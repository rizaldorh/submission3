package com.dicoding.githubapplication3.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.githubapplication3.data.model.Pengguna
import com.dicoding.githubapplication3.databinding.ItemPenggunaBinding

class PenggunaAdapter : RecyclerView.Adapter<PenggunaAdapter.PenggunaViewHolder>() {
    private val daftar = ArrayList<Pengguna>()

    private var onItemTekanHubungikembali: OnItemTekanHubungikembali? = null

    fun setOnItemTekanHubungikembali (onItemTekanHubungikembali: OnItemTekanHubungikembali){
        this.onItemTekanHubungikembali = onItemTekanHubungikembali
    }

    //ketika setDaftar dipanggil di viewModel, daftar pengguna akan dibersihkan, dan diisi dengan
    // data yang baru dan diberitahu kalau dataset berubah
    fun setDaftar(_pengguna: ArrayList<Pengguna>) {
        daftar.clear()
        daftar.addAll(_pengguna)
        notifyDataSetChanged()
    }

    inner class PenggunaViewHolder(val binding: ItemPenggunaBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pengguna: Pengguna) {
            binding.root.setOnClickListener{
                onItemTekanHubungikembali?.onItemDitekan(pengguna)
            }

            binding.apply {
                Glide.with(itemView)
                    .load(pengguna.avatar_url)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .centerCrop()
                    .into(imageViewPengguna)
                textViewPengguna.text = pengguna.login
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PenggunaViewHolder {
        val view = ItemPenggunaBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PenggunaViewHolder((view))
    }

    override fun onBindViewHolder(holder: PenggunaViewHolder, position: Int) {
        holder.bind(daftar[position])
    }

    override fun getItemCount(): Int = daftar.size

    interface OnItemTekanHubungikembali{
        fun onItemDitekan(data: Pengguna)
    }
}