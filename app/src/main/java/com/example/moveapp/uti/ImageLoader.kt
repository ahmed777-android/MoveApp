package com.example.moveapp.uti

import android.app.Activity
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class ImageLoader(var mActivity: Activity) {

    private var mDefaultRequestOptions: RequestOptions = RequestOptions().centerCrop()

    fun loadImage(uri: String, target: ImageView) {
        Glide.with(mActivity).load(uri).apply(mDefaultRequestOptions).into(target);

    }
}