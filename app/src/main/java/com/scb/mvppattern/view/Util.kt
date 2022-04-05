package com.scb.mvppattern.view

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.scb.mvppattern.R


fun getProgressDrawable(context: Context): CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)
}

fun ImageView.loadImageFromUrl(imageUrl: String, progressDrawable: CircularProgressDrawable) {
    val imageLoader = ImageLoader.Builder(this.context)
        .componentRegistry {
            add(SvgDecoder(this@loadImageFromUrl.context))
        }
        .build()

    val imageRequest = ImageRequest.Builder(this.context)
        .placeholder(progressDrawable)
        .data(imageUrl)
        .target(
            onStart = {
                this.setImageDrawable(progressDrawable)
            },
            onSuccess = { result ->
                val bitmap = (result as BitmapDrawable).bitmap
                this.setImageBitmap(bitmap)
            },
            onError = {
                this.setImageDrawable(progressDrawable)
            }
        )
        .build()

    imageLoader.enqueue(imageRequest)
}