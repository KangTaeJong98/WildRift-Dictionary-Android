package com.taetae98.wildriftdictionary.databinding

import android.webkit.WebView
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.flexbox.FlexboxLayoutManager

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

    @JvmStatic
    @BindingAdapter("justifyContent")
    fun setJustifyContent(view: RecyclerView, justifyContent: Int) {
        if (view.layoutManager is FlexboxLayoutManager) {
            (view.layoutManager as FlexboxLayoutManager).justifyContent = justifyContent
        }
    }
}