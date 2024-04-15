package com.anupdey.androidcustomnotifications.util.list

import android.content.Context
import android.widget.RemoteViews
import android.widget.RemoteViewsService
import com.anupdey.androidcustomnotifications.R

class ListProvider(private val context: Context) : RemoteViewsService.RemoteViewsFactory {
    override fun onCreate() {

    }

    override fun onDataSetChanged() {

    }

    override fun onDestroy() {

    }

    override fun getCount(): Int {
        return 1;
    }

    override fun getViewAt(position: Int): RemoteViews {
        val remoteViews = RemoteViews(context.packageName, R.layout.view_notification_image)
        remoteViews.setImageViewResource(R.id.imageView, R.drawable.banner1)
        return remoteViews
    }

    override fun getLoadingView(): RemoteViews? = null

    override fun getViewTypeCount(): Int = 1

    override fun getItemId(position: Int): Long = position.toLong()

    override fun hasStableIds(): Boolean = true
}