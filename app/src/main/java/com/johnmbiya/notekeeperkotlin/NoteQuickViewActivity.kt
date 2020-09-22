package com.johnmbiya.notekeeperkotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_note_quick_view.*

class NoteQuickViewActivity : AppCompatActivity() {

  private var notePosition = POSITION_NOT_SET

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_note_quick_view)

    notePosition = savedInstanceState?.getInt(EXTRA_NOTE_POSITION, POSITION_NOT_SET) ?: intent.getIntExtra(
      EXTRA_NOTE_POSITION, POSITION_NOT_SET)

    initLayout()
    setNote()
  }

  private fun initLayout() {
    deleteButton.setOnClickListener {
      DataManager.notes.removeAt(notePosition)
      finish()
    }
  }

  private fun setNote() {
    if (notePosition != POSITION_NOT_SET) {
      val note = DataManager.notes[notePosition]
      textNoteTitle.text = note.title
      textNoteText.text = note.text
      course.text = note.course?.title
    } else {
      Snackbar.make(note_quick_view_layout, "Error loading note", Snackbar.LENGTH_INDEFINITE)
    }
  }

  override fun onSaveInstanceState(outState: Bundle) {
      super.onSaveInstanceState(outState)
    outState.putInt(EXTRA_NOTE_POSITION, notePosition)
  }

  companion object {
    const val EXTRA_NOTE_POSITION = "notePosition"
    fun getIntent(context: Context, notePosition: Int): Intent {
      val intent = Intent(context, NoteQuickViewActivity::class.java)
      intent.putExtra(EXTRA_NOTE_POSITION, notePosition)
      return intent
    }
  }
}
