package com.projeto.common.base.extensions

import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.DrawableRes
import com.bumptech.glide.Glide

fun View.setMarginTop(padding: Int) {
    val params = this.layoutParams as ViewGroup.MarginLayoutParams
    params.topMargin = padding
    layoutParams = params
}

fun ImageView.load(@DrawableRes drawable: Int) {
    Glide.with(this).load(drawable).into(this)
}