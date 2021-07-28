package com.taetae98.wildriftdictionary.databinding

import android.webkit.WebView
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["imageURL", "errorImage"], requireAll = false)
    fun setImageURL(view: ImageView, url: String?, @DrawableRes error: Int) {
        if (url == null) {
            view.setImageDrawable(null)
            return
        }

        Glide.with(view).load(url).error(error).into(view)
    }

    @JvmStatic
    @BindingAdapter("url")
    fun setURL(view: WebView, url: String?) {
        if (url == null) {
            view.loadDataWithBaseURL(null, "", null, null, null)
            return
        }

        view.loadUrl(url)
    }
}