package com.taetae98.wildriftdictionary.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.ItemAdapter
import com.taetae98.wildriftdictionary.databinding.BindingDialog
import com.taetae98.wildriftdictionary.databinding.DialogItemSearchBinding
import com.taetae98.wildriftdictionary.repository.ItemRepository
import com.taetae98.wildriftdictionary.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ItemSearchDialog : BindingDialog<DialogItemSearchBinding>(R.layout.dialog_item_search) {
    private val viewModel by viewModels<SearchViewModel>()
    private val itemAdapter by lazy {
        ItemAdapter().apply {
            onItemClickListener = {
                findNavController().navigate(ItemSearchDialogDirections.actionItemSearchDialogToItemDialog(it))
            }
        }
    }

    @Inject
    lateinit var itemRepository: ItemRepository

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        onCreateViewModelInput()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        onCreateRecyclerView()

        return binding.root
    }

    override fun onCreateViewDataBinding() {
        super.onCreateViewDataBinding()
        binding.viewModel = viewModel
    }

    private fun onCreateRecyclerView() {
        with(binding.recyclerView) {
            adapter = itemAdapter
        }
    }

    private fun onCreateViewModelInput() {
        viewModel.input.observe(viewLifecycleOwner) {
            itemAdapter.submitList(itemRepository.findLikeName(it))
        }
    }
}