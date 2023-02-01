
package com.example.android.eggtimernotifications.util

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationCompat
import com.example.android.eggtimernotifications.MainActivity
import com.example.android.eggtimernotifications.R
import com.example.android.eggtimernotifications.receiver.SnoozeReceiver

// Notification ID.
private val NOTIFICATION_ID = 0
private val REQUEST_CODE = 0
private val FLAGS = 0

//TODO: Etapa 1.1 função de extensão para enviar mensagens (GIVEN)


fun NotificationManager.sendNotification(messageBody: String, applicationContext: Context) {
    // Criar a intenção de conteúdo para a notificação, que é iniciada
    // TODO: Etapa 1.11 criar intenção
    val contentIntent = Intent(applicationContext, MainActivity::class.java)

    // TODO: Etapa 1.12 criar PendingIntent
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    // TODO: Etapa 2.0 adicionar estilo
    val eggImage = BitmapFactory.decodeResource(
        applicationContext.resources,
        R.drawable.cooked_egg
    )

    val bigPicStyle = NotificationCompat.BigPictureStyle()
        .bigPicture(eggImage)
        .bigLargeIcon(null)

    // TODO: Passo 2.2 adicionar ação de soneca
    val snoozeIntent = Intent(applicationContext, SnoozeReceiver::class.java)
    val snoozePendingIntent: PendingIntent = PendingIntent.getBroadcast(
        applicationContext,
        REQUEST_CODE,
        snoozeIntent,
        FLAGS)

    // TODO: Etapa 1.2 obter uma instância do NotificationCompat.Builder
    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.egg_notification_channel_id)
    )


    // TODO: Passo 1.3 definir título, texto e ícone para construtor
        .setSmallIcon(R.drawable.cooked_egg)
        .setContentTitle(applicationContext.getString(R.string.notification_title))
        .setContentText(messageBody)
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(true)

    // TODO: Etapa 1.13 definir a intenção do conteúdo

        // TODO: Etapa 2.1 Adicionar estilo ao construtor
        .setStyle(bigPicStyle)
        .setLargeIcon(eggImage)

        // TODO: Passo 2.3 adicionar ação de soneca
        .addAction(
            R.drawable.egg_icon,
            applicationContext.getString(R.string.snooze),
            snoozePendingIntent
        )
        // TODO: Etapa 2.5 definir prioridade
        .setPriority(NotificationCompat.PRIORITY_HIGH)

    // TODO: Etapa 1.4 Notificação de chamada
    notify(NOTIFICATION_ID, builder.build())



}

// TODO: Passo 1.14 Cancelar todas as notificações

fun NotificationManager.cancelNotifications() {
    cancelAll()
}
