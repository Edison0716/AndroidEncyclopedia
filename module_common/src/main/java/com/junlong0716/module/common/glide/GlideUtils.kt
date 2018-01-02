package com.htxcsoft.corelibrary.glide

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import jp.wasabeef.glide.transformations.BlurTransformation


/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午12:55 2017/11/30
 *@modified by:
 */
object GlideUtils {

    fun showImage(context: Context, path: Any?, imageView: ImageView) {
        GlideApp.with(context).load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
    }

    fun showImage(context: Context, path: Any?, imageView: ImageView, placeholder: Int) {
        GlideApp.with(context).load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .placeholder(placeholder)
                .into(imageView)
    }

    fun showImage(context: Context, path: Any?, errorHolder: Int, imageView: ImageView) {
        GlideApp.with(context).load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .error(errorHolder)
                .into(imageView)
    }

    fun showImageFade(context: Context, path: Any?, imageView: ImageView) {
        GlideApp.with(context).load(path)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView)
    }

    fun showBlurImage(context: Context, path: Any?, imageView: ImageView, blurRadius: Int) {
        GlideApp.with(context).load(path)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(bitmapTransform(BlurTransformation(blurRadius)))
                .into(imageView)
    }

    fun showBlurImageFade(context: Context, path: Any?, imageView: ImageView, blurRadius: Int) {
        GlideApp.with(context).load(path)
                .transition(DrawableTransitionOptions.withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .apply(bitmapTransform(BlurTransformation(blurRadius)))
                .into(imageView)
    }
}