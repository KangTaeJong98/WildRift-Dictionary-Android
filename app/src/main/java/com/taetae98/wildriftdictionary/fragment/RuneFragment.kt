package com.taetae98.wildriftdictionary.fragment

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.findNavController
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.RuneAdapter
import com.taetae98.wildriftdictionary.databinding.BindingFragment
import com.taetae98.wildriftdictionary.databinding.FragmentRuneBinding
import com.taetae98.wildriftdictionary.dto.Rune
import com.taetae98.wildriftdictionary.repository.RuneRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RuneFragment : BindingFragment<FragmentRuneBinding>(R.layout.fragment_rune) {
    @Inject
    lateinit var runeRepository: RuneRepository

    private val keyStoneAdapter by lazy {
        RuneAdapter().apply {
            submitList(runeRepository.findByGroup(Rune.Group.KEY_STONE))
            onRuneClickListener = {
                findNavController().navigate(RuneFragmentDirections.actionRuneFragmentToRuneDialog(it))
            }
        }
    }

    private val dominationAdapter by lazy {
        RuneAdapter().apply {
            submitList(runeRepository.findByGroup(Rune.Group.DOMINATION))
            onRuneClickListener = {
                findNavController().navigate(RuneFragmentDirections.actionRuneFragmentToRuneDialog(it))
            }
        }
    }

    private val resolveAdapter by lazy {
        RuneAdapter().apply {
            submitList(runeRepository.findByGroup(Rune.Group.RESOLVE))
            onRuneClickListener = {
                findNavController().navigate(RuneFragmentDirections.actionRuneFragmentToRuneDialog(it))
            }
        }
    }

    private val inspirationAdapter by lazy {
        RuneAdapter().apply {
            submitList(runeRepository.findByGroup(Rune.Group.INSPIRATION))
            onRuneClickListener = {
                findNavController().navigate(RuneFragmentDirections.actionRuneFragmentToRuneDialog(it))
            }
        }
    }

    init {
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_rune_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            R.id.search -> {
                findNavController().navigate(RuneFragmentDirections.actionRuneFragmentToRuneSearchDialog())
                true
            }
            else -> {
                false
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        onCreateKeyStoneRecyclerView()
        onCreateDominationRecyclerView()
        onCreateResolveRecyclerView()
        onCreateInspirationRecyclerView()

        return binding.root
    }

    private fun onCreateKeyStoneRecyclerView() {
        with(binding.keyStoneRecyclerView) {
            adapter = keyStoneAdapter
        }
    }

    private fun onCreateDominationRecyclerView() {
        with(binding.dominationRecyclerView) {
            adapter = dominationAdapter
        }
    }

    private fun onCreateResolveRecyclerView() {
        with(binding.resolveRecyclerView) {
            adapter = resolveAdapter
        }
    }

    private fun onCreateInspirationRecyclerView() {
        with(binding.inspirationRecyclerView) {
            adapter = inspirationAdapter
        }
    }
}