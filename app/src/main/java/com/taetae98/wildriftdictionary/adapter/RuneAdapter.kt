package com.taetae98.wildriftdictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.taetae98.wildriftdictionary.base.BaseAdapter
import com.taetae98.wildriftdictionary.base.BaseHolder
import com.taetae98.wildriftdictionary.databinding.HolderRuneBinding
import com.taetae98.wildriftdictionary.dto.Rune

class RuneAdapter : BaseAdapter<Rune>(itemCallback) {
    companion object {
        private val itemCallback = object : DiffUtil.ItemCallback<Rune>() {
            override fun areItemsTheSame(oldItem: Rune, newItem: Rune): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: Rune, newItem: Rune): Boolean {
                return oldItem == newItem
            }
        }
    }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<out ViewDataBinding, Rune> {
        return RuneHolder(
            HolderRuneBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).hashCode().toLong()
    }

    inner class RuneHolder(binding: HolderRuneBinding) : BaseHolder<HolderRuneBinding, Rune>(binding) {
        override fun onBind(element: Rune) {
            super.onBind(element)
            binding.rune = element
        }
    }
}