package com.example.androiddependencyinjection.helperUtil

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.example.androiddependencyinjection.R

@BindingAdapter("app:loadUrl")
fun loadImage(view: ImageView, url: String?) {
    val circularProgressDrawable = CircularProgressDrawable(view.context)
    circularProgressDrawable.strokeWidth = 5f
    circularProgressDrawable.centerRadius = 30f
    circularProgressDrawable.start()
    url?.let {
        Glide.with(view.context).load(url).placeholder(circularProgressDrawable).circleCrop()
            .error(R.drawable.ic_launcher_background).into(view)
    }
}