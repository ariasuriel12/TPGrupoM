package com.example.climaapp

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.app.NotificationChannel
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

private const val CHANNEL_ID = "LOGIN_CHANNEL_ID"
private const val NOTIFICATION_ID_REMEMBER = 101

object Notificaciones {

    @SuppressLint("InlinedApi")
    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Notificaciones de Sesión"
            val descriptionText = "Notificaciones relacionadas con el inicio de sesion."
            val importance = NotificationManager.IMPORTANCE_LOW
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    @SuppressLint("MissingPermission")
    fun showRememberUserConfirmation(context: Context) {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Credenciales Guardadas")
            .setContentText("Tu usuario y contraseña han sido guardados para el próximo inicio de sesión.")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true) // Se cierra al hacer clic

        with(NotificationManagerCompat.from(context)) {
            notify(NOTIFICATION_ID_REMEMBER, builder.build())
        }
    }
}