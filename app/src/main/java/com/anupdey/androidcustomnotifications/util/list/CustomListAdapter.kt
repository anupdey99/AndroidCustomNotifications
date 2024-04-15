package com.anupdey.androidcustomnotifications.util.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import com.anupdey.androidcustomnotifications.R
import com.anupdey.androidcustomnotifications.data.CarouselData
import com.bumptech.glide.Glide

class CustomListAdapter(
    private val context: Context,
    private val resourceLayout: Int,
    private val items: List<CarouselData>
): ArrayAdapter<CarouselData>(context, resourceLayout, items) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var v = convertView
        if (v == null) {
            val inflater = LayoutInflater.from(context)
            v = inflater.inflate(resourceLayout, null)
        }

        val imageView: ImageView = v!!.findViewById(R.id.imageView)
        val item = getItem(position)

        Glide.with(context)
            .load(item?.image)
            .into(imageView)

        return v!!
    }

}