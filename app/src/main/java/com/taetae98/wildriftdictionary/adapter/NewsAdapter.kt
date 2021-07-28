package com.taetae98.wildriftdictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.taetae98.wildriftdictionary.base.BaseAdapter
import com.taetae98.wildriftdictionary.base.BaseHolder
import com.taetae98.wildriftdictionary.databinding.HolderNewsBinding
import com.taetae98.wildriftdictionary.dto.News

class NewsAdapter : BaseAdapter<News>(itemCallback) {
    companion object {
        private val itemCallback = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }
        }
    }

    var onNewsClickListener: ((News) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<out ViewDataBinding, News> {
        return NewsHolder(
            HolderNewsBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class NewsHolder(binding: HolderNewsBinding) : BaseHolder<HolderNewsBinding, News>(binding) {
        init {
            onCreateOnClick()
        }

        override fun onBind(element: News) {
            super.onBind(element)
            binding.news = element
        }

        private fun onCreateOnClick() {
            binding.setOnClick {
                onNewsClickListener?.invoke(element)
            }
        }
    }
}