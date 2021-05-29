package com.dicoding.githubapplication3.ui.pengaturan

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.githubapplication3.data.model.Pengingat
import com.dicoding.githubapplication3.databinding.ActivityPengaturanBinding
import com.dicoding.githubapplication3.penerima.PengingatReceiver
import com.dicoding.githubapplication3.preferences.PengingatPreference

class PengaturanActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPengaturanBinding
    private lateinit var pengingat: Pengingat
    private lateinit var pengingatReceiver: PengingatReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPengaturanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val pengingatPreference = PengingatPreference(this)
        if (pengingatPreference.getPengingat().isTeringat) {
            binding.switchAlarm.isChecked = true
        } else {
            binding.switchAlarm.isChecked = false
        }

        pengingatReceiver = PengingatReceiver()

        binding.switchAlarm.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                simpanPengingat(true)
                pengingatReceiver.setAlarmMengulang(
                    this,
                    "AlarmBerulang",
                    "09:00",
                    "Pengingat Github"
                )
            } else {
                simpanPengingat(false)
                pengingatReceiver.batalkanAlarm(this)
            }
        }
    }

    private fun simpanPengingat(kondisi: Boolean) {
        val pengingatPreference = PengingatPreference(this)
        pengingat = Pengingat()

        pengingat.isTeringat = kondisi
        pengingatPreference.setPengingat(pengingat)
    }
}
