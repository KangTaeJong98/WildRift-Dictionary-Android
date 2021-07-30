package com.taetae98.wildriftdictionary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.DrawableRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.base.BaseAdapter
import com.taetae98.wildriftdictionary.base.BaseHolder
import com.taetae98.wildriftdictionary.databinding.HolderChampionPageBinding
import com.taetae98.wildriftdictionary.dto.Champion
import com.taetae98.wildriftdictionary.repository.ChampionRepository
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@FragmentScoped
class ChampionPageAdapter @Inject constructor(
    private val championRepository: ChampionRepository
) : BaseAdapter<ChampionPageAdapter.ChampionPage>(itemCallback) {
    companion object {
        private val itemCallback = object : DiffUtil.ItemCallback<ChampionPage>() {
            override fun areItemsTheSame(oldItem: ChampionPage, newItem: ChampionPage): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(oldItem: ChampionPage, newItem: ChampionPage): Boolean {
                return oldItem == newItem
            }
        }
    }

    var onChampionClickListener: ((Champion) -> Unit)? = null

    init {
        setHasStableIds(true)
        submitList(
            listOf(
                ChampionPage(R.drawable.icon_all, Champion.Line.ALL),
                ChampionPage(R.drawable.icon_top, Champion.Line.TOP),
                ChampionPage(R.drawable.icon_jungle, Champion.Line.JUNGLE),
                ChampionPage(R.drawable.icon_mid, Champion.Line.MID),
                ChampionPage(R.drawable.icon_ad, Champion.Line.AD),
                ChampionPage(R.drawable.icon_support, Champion.Line.SUPPORTER),
            )
        )
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder<out ViewDataBinding, ChampionPage> {
        return ChampionPageHolder(
            HolderChampionPageBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemId(position: Int): Long {
        return getItem(position).line.hashCode().toLong()
    }

    inner class ChampionPageHolder(binding: HolderChampionPageBinding) : BaseHolder<HolderChampionPageBinding, ChampionPage>(binding) {
        private val championAdapter by lazy {
            ChampionAdapter().apply {
                submitList(championRepository.findByLines(element.line))
                onChampionClickListener = {
                    this@ChampionPageAdapter.onChampionClickListener?.invoke(it)
                }
            }
        }

        override fun onBind(element: ChampionPage) {
            super.onBind(element)
            onCreateRecyclerView()
        }

        private fun onCreateRecyclerView() {
            with(binding.recyclerView) {
                adapter = championAdapter
            }
        }
    }

    data class ChampionPage(
        @DrawableRes
        val icon: Int,
        val line: Champion.Line
    )
}