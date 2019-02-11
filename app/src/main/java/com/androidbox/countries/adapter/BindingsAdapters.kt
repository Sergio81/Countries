package com.androidbox.countries.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ahmadrosid.svgloader.SvgLoader
import com.androidbox.countries.R
import android.content.ContextWrapper
import android.app.Activity
import android.view.View


private fun getActivity(view: View): Activity? {
    var context = view.context
    while (context is ContextWrapper) {
        if (context is Activity) {
            return context
        }
        context = context.baseContext
    }
    return null
}

@BindingAdapter("android:svg_src")
fun setImageViewResource(imageView: ImageView, urlResource: String) {
    SvgLoader.pluck()
        .with(getActivity(imageView))
        .setPlaceHolder(
            R.drawable.ic_image_placeholder_black_24dp,
            R.drawable.ic_image_placeholder_black_24dp)
        .load(urlResource, imageView)
}