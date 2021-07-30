package com.taetae98.wildriftdictionary.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.RuneAdapter
import com.taetae98.wildriftdictionary.databinding.BindingDialog
import com.taetae98.wildriftdictionary.databinding.DialogRuneSearchBinding
import com.taetae98.wildriftdictionary.repository.RuneRepository
import com.taetae98.wildriftdictionary.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RuneSearchDialog : BindingDialog<DialogRuneSearchBinding>(R.layout.dialog_rune_search) {
    private val viewModel by viewModels<SearchViewModel>()
    private val runeAdapter by lazy {
        RuneAdapter().apply {
            onRuneClickListener = {
                findNavController().navigate(RuneSearchDialogDirections.actionRuneSearchDialogToRuneDialog(it))
            }
        }
    }

    @Inject
    lateinit var runeRepository: RuneRepository

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
            adapter = runeAdapter
        }
    }

    private fun onCreateViewModelInput() {
        viewModel.input.observe(viewLifecycleOwner) {
            runeAdapter.submitList(runeRepository.findLikeName(it))
        }
    }
}