package com.taetae98.wildriftdictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.taetae98.wildriftdictionary.base.BaseAdapter
import com.taetae98.wildriftdictionary.base.BaseHolder
import com.taetae98.wildriftdictionary.databinding.HolderAbilityBinding
import com.taetae98.wildriftdictionary.dto.Ability

class AbilityAdapter : BaseAdapter<Ability>(itemCallback) {
    companion object {
        private val itemCallback = object : DiffUtil.ItemCallback<Ability>() {
            override fun areItemsTheSame(oldItem: Ability, newItem: Ability): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: Ability, newItem: Ability): Boolean {
                return oldItem == newItem
            }
        }
    }

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<out ViewDataBinding, Ability> {
        return AbilityHolder(
            HolderAbilityBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).hashCode().toLong()
    }

    inner class AbilityHolder(binding: HolderAbilityBinding) : BaseHolder<HolderAbilityBinding, Ability>(binding) {
        override fun onBind(element: Ability) {
            super.onBind(element)
            binding.ability = element
        }
    }
}