package com.johnmbiya.notekeeperkotlin

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.RemoteInput
import androidx.core.content.ContextCompat

/**
 * Helper class for showing and canceling reminder
 * notifications.
 *
 *
 * This class makes heavy use of the [NotificationCompat.Builder] helper
 * class to create notifications in a backward-compatible way.
 */
object ReminderNotification {
  /**
   * The unique identifier for this type of notification.
   */
  private const val NOTIFICATION_TAG = "Reminder"

  const val REMINDER_CHANNEL = "reminders"

  const val KEY_TEXT_REPLY = "keyTextReply"

  /**
   * Shows the notification, or updates a previously shown notification of
   * this type, with the given parameters.
   *
   * @see .cancel
   */
  fun notify(context: Context, note: NoteInfo, notePosition: Int) {

    val intent = Intent(context, NoteActivity::class.java)
    intent.putExtra(NOTE_POSITION, notePosition)

    val pendingIntent = TaskStackBuilder.create(context)
        .addNextIntentWithParentStack(intent)
        .getPendingIntent(0, PendingIntent.FLAG_CANCEL_CURRENT)

    val message1 = NotificationCompat.MessagingStyle.Message(
        note.comments[0].comment,
        note.comments[0].timestamp,
        note.comments[0].name)
    val message2 = NotificationCompat.MessagingStyle.Message(
        note.comments[1].comment,
        note.comments[1].timestamp,
        note.comments[1].name)
    val message3 = NotificationCompat.MessagingStyle.Message(
        note.comments[2].comment,
        note.comments[2].timestamp,
        note.comments[2].name)

    val remoteInput = RemoteInput.Builder(KEY_TEXT_REPLY)
        .setLabel("Add Note")
        .build()

    val replyIntent = Intent(context, NotificationBroadcastReceiver::class.java)
    replyIntent.putExtra(NOTE_POSITION, notePosition)

    val replyPendingIntent = PendingIntent.getBroadcast(context,
        100,
        replyIntent,
        PendingIntent.FLAG_UPDATE_CURRENT)

    val replyAction = NotificationCompat.Action.Builder(R.drawable.ic_reply_black_24dp,
        "Add Note", replyPendingIntent)
        .addRemoteInput(remoteInput)
        .build()

    val builder = NotificationCompat.Builder(context, REMINDER_CHANNEL)

        // Set appropriate defaults for the notification light, sound,
        // and vibration.
        .setDefaults(Notification.DEFAULT_ALL)

        // Set required fields, including the small icon, the
        // notification title, and text.
        .setSmallIcon(R.drawable.ic_stat_reminder)
        .setContentTitle("Comment from " + note.comments[0].name)
        .setContentText(note.comments[0].comment)

        // All fields below this line are optional.

        // Use a default priority (recognized on devices running Android
        // 4.1 or later)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        // Set ticker text (preview) information for this notification.
        .setTicker("Note Comments")

        // Set the pending intent to be initiated when the user touches
        // the notification.
        .setContentIntent(pendingIntent)

        // Automatically dismiss the notification when it is touched.
        .setAutoCancel(true)

        .setColor(ContextCompat.getColor(context, R.color.darkOrange))

        .setColorized(true)

        .setOnlyAlertOnce(true)

        .setStyle(NotificationCompat.MessagingStyle("You")
            .setConversationTitle(note.title)
            .addMessage(message3)
            .addMessage(message2)
            .addMessage(message1))

        .addAction(replyAction)

    notify(context, builder.build())
  }

  @TargetApi(Build.VERSION_CODES.ECLAIR)
  private fun notify(context: Context, notification: Notification) {
    val nm = context
        .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
      nm.notify(NOTIFICATION_TAG, 0, notification)
    } else {
      nm.notify(NOTIFICATION_TAG.hashCode(), notification)
    }
  }

  /**
   * Cancels any notifications of this type previously shown using
   * [.notify].
   */
  @TargetApi(Build.VERSION_CODES.ECLAIR)
  fun cancel(context: Context) {
    val nm = context
        .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ECLAIR) {
      nm.cancel(NOTIFICATION_TAG, 0)
    } else {
      nm.cancel(NOTIFICATION_TAG.hashCode())
    }
  }
}
