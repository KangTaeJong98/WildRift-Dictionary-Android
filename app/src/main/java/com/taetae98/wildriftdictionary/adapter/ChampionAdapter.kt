package com.taetae98.wildriftdictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.taetae98.wildriftdictionary.base.BaseAdapter
import com.taetae98.wildriftdictionary.base.BaseHolder
import com.taetae98.wildriftdictionary.databinding.HolderChampionBinding
import com.taetae98.wildriftdictionary.dto.Champion

class ChampionAdapter : BaseAdapter<Champion>(itemCallback)  {
    companion object {
        val itemCallback = object : DiffUtil.ItemCallback<Champion>() {
            override fun areItemsTheSame(oldItem: Champion, newItem: Champion): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: Champion, newItem: Champion): Boolean {
                return oldItem == newItem
            }
        }
    }

    init {
        setHasStableIds(true)
    }

    var onChampionClickListener: ((Champion) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<out ViewDataBinding, Champion> {
        return ChampionHolder(
            HolderChampionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).id.hashCode().toLong()
    }

    inner class ChampionHolder(binding: HolderChampionBinding) : BaseHolder<HolderChampionBinding, Champion>(binding) {
        init {
            onCreateOnClick()
        }

        override fun onBind(element: Champion) {
            super.onBind(element)
            binding.champion = element
        }

        private fun onCreateOnClick() {
            binding.setOnClick {
                onChampionClickListener?.invoke(element)
            }
        }
    }
}