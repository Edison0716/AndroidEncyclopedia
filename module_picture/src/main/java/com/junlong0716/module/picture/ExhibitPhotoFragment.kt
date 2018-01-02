package com.htxcsoft.module_photo

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.github.chrisbanes.photoview.PhotoView
import com.htxcsoft.corelibrary.glide.GlideApp
import com.junlong0716.module.common.glide.ProgressInterceptor
import com.junlong0716.module.picture.R
import com.junlong0716.module.picture.widget.CircleProgressView

/**
 *@author: 巴黎没有摩天轮Li
 *@description:
 *@date: Created in 下午1:21 2017/12/27
 *@modified by:
 */
class ExhibitPhotoFragment : Fragment() {
    private lateinit var photoUrl: String
    private lateinit var photoName: String
    private lateinit var photoView: PhotoView
    private lateinit var progressView: CircleProgressView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            photoUrl = bundle.getString("photoUrl")
            photoName = bundle.getString("photoName")
        }
        Log.d("Fragment_Create", photoName)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d("Fragment_onCreateView", photoName)
        var view = inflater.inflate(R.layout.item_exhibit_photo_layout, container, false)
        initView(view)
        return view
    }

    private fun initView(view: View?) {
        photoView = view!!.findViewById<PhotoView>(R.id.photoView)
        progressView = view.findViewById<CircleProgressView>(R.id.cpv_progress)

        ProgressInterceptor.addListener(photoUrl) { progress ->
            progressView.progress = progress
        }

        GlideApp.with(activity!!.applicationContext)
                .load(photoUrl)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(object : SimpleTarget<Drawable>() {
                    override fun onResourceReady(resource: Drawable?, transition: Transition<in Drawable>?) {
                        photoView.setImageDrawable(resource)
                        ProgressInterceptor.removeListener(photoUrl)
                        progressView.visibility = View.GONE
                    }

                    override fun onLoadFailed(errorDrawable: Drawable?) {
                        ProgressInterceptor.removeListener(photoUrl)
                        progressView.visibility = View.GONE
                    }
                })

    }

    companion object {
        fun newInstance(photoUrl: String, photoName: String): ExhibitPhotoFragment {
            val bundle = Bundle()
            bundle.putString("photoUrl", photoUrl)
            bundle.putString("photoName", photoName)
            val exhibitPhotoFragment = ExhibitPhotoFragment()
            exhibitPhotoFragment.arguments = bundle
            return exhibitPhotoFragment
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Fragment_onDestroy", photoName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d("Fragment_onDestroyView", photoName)
    }

    @SuppressLint("LongLogTag")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d("Fragment_onActivityCreated", photoName)
    }
}