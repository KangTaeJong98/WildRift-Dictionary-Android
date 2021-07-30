package com.taetae98.wildriftdictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.taetae98.wildriftdictionary.base.BaseAdapter
import com.taetae98.wildriftdictionary.base.BaseHolder
import com.taetae98.wildriftdictionary.databinding.HolderSkillBinding
import com.taetae98.wildriftdictionary.dto.Skill

class SkillAdapter : BaseAdapter<Skill>(itemCallback) {
    companion object {
        private val itemCallback = object : DiffUtil.ItemCallback<Skill>() {
            override fun areItemsTheSame(oldItem: Skill, newItem: Skill): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: Skill, newItem: Skill): Boolean {
                return oldItem == newItem
            }
        }
    }

    var onSkillClickListener: ((Skill) -> Unit)? = null

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).hashCode().toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<out ViewDataBinding, Skill> {
        return SkillHolder(
            HolderSkillBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class SkillHolder(binding: HolderSkillBinding) : BaseHolder<HolderSkillBinding, Skill>(binding) {
        init {
            onCreateOnClick()
        }

        override fun onBind(element: Skill) {
            super.onBind(element)
            binding.skill = element
        }

        private fun onCreateOnClick() {
            binding.setOnClick {
                onSkillClickListener?.invoke(element)
            }
        }
    }
}