package com.anupdey.androidcustomnotifications.util.list

import android.content.Intent
import android.widget.RemoteViewsService
import com.anupdey.androidcustomnotifications.util.list.ListProvider

class ListService: RemoteViewsService() {
    override fun onGetViewFactory(intent: Intent?): RemoteViewsFactory {
        return ListProvider(applicationContext)
    }
}