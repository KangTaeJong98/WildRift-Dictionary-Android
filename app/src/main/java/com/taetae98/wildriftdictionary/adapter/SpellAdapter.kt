package com.taetae98.wildriftdictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.taetae98.wildriftdictionary.base.BaseAdapter
import com.taetae98.wildriftdictionary.base.BaseHolder
import com.taetae98.wildriftdictionary.databinding.HolderSpellBinding
import com.taetae98.wildriftdictionary.dto.Spell

class SpellAdapter : BaseAdapter<Spell>(itemCallback) {
    companion object {
        private val itemCallback = object : DiffUtil.ItemCallback<Spell>() {
            override fun areItemsTheSame(oldItem: Spell, newItem: Spell): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: Spell, newItem: Spell): Boolean {
                return oldItem == newItem
            }
        }
    }

    var onSpellClickListener: ((Spell) -> Unit)? = null

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<out ViewDataBinding, Spell> {
        return SpellHolder(
            HolderSpellBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).hashCode().toLong()
    }

    inner class SpellHolder(binding: HolderSpellBinding) : BaseHolder<HolderSpellBinding, Spell>(binding) {
        init {
            onCreateOnClick()
        }

        override fun onBind(element: Spell) {
            super.onBind(element)
            binding.spell = element
        }

        private fun onCreateOnClick() {
            binding.setOnClick {
                onSpellClickListener?.invoke(element)
            }
        }
    }
}