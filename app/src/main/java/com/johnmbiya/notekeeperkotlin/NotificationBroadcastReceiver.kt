package com.johnmbiya.notekeeperkotlin

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.RemoteInput

class NotificationBroadcastReceiver : BroadcastReceiver() {

  override fun onReceive(context: Context, intent: Intent) {
    // This method is called when the BroadcastReceiver is receiving an Intent broadcast.
    val bundle = RemoteInput.getResultsFromIntent(intent)
    if (bundle != null) {
      val notePosition = intent.getIntExtra(NOTE_POSITION, POSITION_NOT_SET)
      val text = bundle
          .getCharSequence(ReminderNotification.KEY_TEXT_REPLY)?.toString()?:""
      DataManager
          .notes[notePosition]
          .comments.add(0, NoteComment(null, text, System.currentTimeMillis()))
        ReminderNotification.notify(
            context,
            DataManager.notes[notePosition],
            notePosition
        )
    }
  }
}
