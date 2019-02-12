package com.androidbox.countries.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.ahmadrosid.svgloader.SvgLoader
import com.androidbox.countries.R
import android.content.ContextWrapper
import android.app.Activity
import android.view.View

/***
 * Helper function to get the activity from the view
 */
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

/***
 *  Get SVG from internet and set it to the view
 */
@BindingAdapter("android:svg_src")
fun setImageViewResource(imageView: ImageView, urlResource: String) {
    SvgLoader.pluck()
        .with(getActivity(imageView))
        .setPlaceHolder(
            R.drawable.ic_image_placeholder_black_24dp,
            R.drawable.ic_image_placeholder_black_24dp)
        .load(urlResource, imageView)
}