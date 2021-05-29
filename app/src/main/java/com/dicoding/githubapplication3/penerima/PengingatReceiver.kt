package com.dicoding.githubapplication3.penerima

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationCompat
import com.dicoding.githubapplication3.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class PengingatReceiver : BroadcastReceiver() {

    companion object {
        private const val NOTIFICATION_ID = 1
        private const val CHANNEL_ID = "channel_1"
        private const val CHANNEL_NAME = "pengingat github"
        private const val TIME_FORMAT = "HH:mm"
        const val EXTRA_MESSAGE = "extra_message"
        const val EXTRA_TYPE = "extra_type"
        private const val REPEATING_ID = 101
    }

    override fun onReceive(context: Context, intent: Intent) {
        // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
        kirimNotifikasi(context)
    }

    private fun kirimNotifikasi(context: Context) {
        val intent =
            context.packageManager.getLaunchIntentForPackage("com.dicoding.githubapplication3")
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val notifManajer =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentIntent(pendingIntent)
            .setSmallIcon(R.drawable.ic_pengingat)
            .setContentTitle(context.resources.getString(R.string.app_name))
            .setContentText("Temukan pengguna favoritmu!")
            .setAutoCancel(true)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            )

            builder.setChannelId(CHANNEL_ID)
            notifManajer.createNotificationChannel(channel)
        }

        val notifikasi = builder.build()
        notifManajer.notify(NOTIFICATION_ID, notifikasi)
    }

    fun setAlarmMengulang(context: Context, type: String, time: String, message: String) {
        if (isTanggalInvalid(time, TIME_FORMAT)) return
        val pengingatManajer = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val intent = Intent(context, PengingatReceiver::class.java)
        intent.putExtra(EXTRA_MESSAGE, message)
        intent.putExtra(EXTRA_TYPE, type)
        val waktuArray = time.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val kalendar = Calendar.getInstance()
        kalendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(waktuArray[0]))
        kalendar.set(Calendar.MINUTE, Integer.parseInt(waktuArray[1]))
        kalendar.set(Calendar.SECOND, 0)

        val pendingIntent = PendingIntent.getBroadcast(context, REPEATING_ID, intent, 0)
        pengingatManajer.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            kalendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
        Toast.makeText(context, "Alarm Berulang Aktif", Toast.LENGTH_SHORT).show()
    }

    private fun isTanggalInvalid(time: String, timeFormat: String): Boolean {
        return try {
            val tanggalformat = SimpleDateFormat(timeFormat, Locale.getDefault())
            tanggalformat.isLenient = false
            tanggalformat.parse(time)
            false
        } catch (e: ParseException) {
            true
        }

    }

    fun batalkanAlarm(context: Context) {
        val pengingatManajer = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, PengingatReceiver::class.java)
        val permintaanKode = REPEATING_ID
        val pendingIntent = PendingIntent.getBroadcast(context, permintaanKode, intent, 0)
        pendingIntent.cancel()
        pengingatManajer.cancel(pendingIntent)
        Toast.makeText(context, "Alarm Berulang batal", Toast.LENGTH_SHORT).show()
    }
}