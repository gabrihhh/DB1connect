package br.com.fiap.sciconnect.service
import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import br.com.fiap.sciconnect.R
import kotlin.random.Random

class NotificationHandler(private val context: Context) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)
    private val notificationChannelID = "notification_channel_id"

    // SIMPLE NOTIFICATION
    fun showSimpleNotification() {
        val notification = NotificationCompat.Builder(context, notificationChannelID)
            .setContentTitle("você deu um MATCH!")
            .setContentText("Envie uma mensagem para seu novo colega")
            .setSmallIcon(R.drawable.like)
            .setPriority(NotificationManager.IMPORTANCE_HIGH)
            .setAutoCancel(true)
            .build()  // finalizes the creation

        notificationManager.notify(Random.nextInt(), notification)
    }
}