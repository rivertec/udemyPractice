package com.liam.udemypractice.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.liam.udemypractice.GlideApp

@BindingAdapter("imageUrl")
fun loadImage(view: ImageView, imageUrl: String) {
    GlideApp
        .with(view)
        .load(imageUrl)
        .into(view)
}