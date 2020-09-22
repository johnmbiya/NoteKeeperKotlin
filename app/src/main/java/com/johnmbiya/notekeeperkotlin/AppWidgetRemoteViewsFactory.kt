package com.johnmbiya.notekeeperkotlin

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.RemoteViews
import android.widget.RemoteViewsService

class AppWidgetRemoteViewsFactory(val context: Context) :
    RemoteViewsService.RemoteViewsFactory {

  override fun onCreate() {
  }

  override fun onDataSetChanged() {
  }

  override fun getLoadingView(): RemoteViews? {
    return null
  }

  override fun getItemId(position: Int): Long {
    return position.toLong()
  }

  override fun hasStableIds(): Boolean {
    return true
  }

  override fun getViewAt(position: Int): RemoteViews {
    val rv = RemoteViews(context.packageName, R.layout.item_note_widget)
    rv.setTextViewText(R.id.note_title, DataManager.notes[position].title)

    val extras = Bundle()
    extras.putInt(NOTE_POSITION, position)
    val fillInIntent = Intent()
    fillInIntent.putExtras(extras)
    rv.setOnClickFillInIntent(R.id.widget_item, fillInIntent)

    return rv
  }

  override fun getCount(): Int {
    return DataManager.notes.size
  }

  override fun getViewTypeCount(): Int {
    return 1
  }

  override fun onDestroy() {
  }

}

