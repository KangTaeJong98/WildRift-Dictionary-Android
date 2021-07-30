package com.taetae98.wildriftdictionary.fragment

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.ItemPageAdapter
import com.taetae98.wildriftdictionary.databinding.BindingFragment
import com.taetae98.wildriftdictionary.databinding.FragmentItemBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ItemFragment : BindingFragment<FragmentItemBinding>(R.layout.fragment_item) {
    @Inject
    lateinit var itemPageAdapter: ItemPageAdapter

    init {
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_champion_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.search -> {
                findNavController().navigate(ItemFragmentDirections.actionItemFragmentToItemSearchDialog())
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        onCreateViewPager()
        onCreateTabLayoutMediator()

        return binding.root
    }

    private fun onCreateViewPager() {
        with(binding.viewPager) {
            adapter = itemPageAdapter
        }

        itemPageAdapter.onItemClickListener = {
            findNavController().navigate(ItemFragmentDirections.actionItemFragmentToItemDialog(it))
        }
    }

    private fun onCreateTabLayoutMediator() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.setIcon(itemPageAdapter.currentList[position].icon)
        }.attach()
    }
}