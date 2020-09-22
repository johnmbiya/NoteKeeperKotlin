package com.johnmbiya.notekeeperkotlin

import android.content.Intent
import android.widget.RemoteViewsService
import com.johnmbiya.notekeeperkotlin.AppWidgetRemoteViewsFactory

class AppWidgetRemoteViewsService : RemoteViewsService() {
  override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
    return AppWidgetRemoteViewsFactory(applicationContext)
  }
}