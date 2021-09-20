package com.samples.flow.presentation.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load

@BindingAdapter("app:image")
fun bindImage(imageView: ImageView, url: String?) {
    if (url != null && url.isNotEmpty()) {
        imageView.load(url)
    }
}