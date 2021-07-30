package com.taetae98.wildriftdictionary.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.*
import com.taetae98.wildriftdictionary.databinding.BindingFragment
import com.taetae98.wildriftdictionary.databinding.FragmentChampionInformationBinding
import com.taetae98.wildriftdictionary.repository.ChampionInformationRepository
import com.taetae98.wildriftdictionary.viewmodel.ChampionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChampionInformationFragment : BindingFragment<FragmentChampionInformationBinding>(R.layout.fragment_champion_information) {
    private val viewModel by activityViewModels<ChampionViewModel>()

    private val itemAdapter by lazy {
        ItemAdapter().apply {
            submitList(viewModel.information.value?.item)
            onItemClickListener = {
                findNavController().navigate(ChampionInformationFragmentDirections.actionChampionInformationFragmentToItemDialog(it))
            }
        }
    }

    private val runeAdapter by lazy {
        RuneAdapter().apply {
            submitList(viewModel.information.value?.rune)
            onRuneClickListener = {
                findNavController().navigate(ChampionInformationFragmentDirections.actionChampionInformationFragmentToRuneDialog(it))
            }
        }
    }

    private val spellAdapter by lazy {
        SpellAdapter().apply {
            submitList(viewModel.information.value?.spell)
        }
    }

    private val abilityAdapter by lazy {
        AbilityAdapter().apply {
            submitList(viewModel.information.value?.ability)
        }
    }

    private val subAbilityAdapter by lazy {
        AbilityAdapter().apply {
            submitList(viewModel.information.value?.subAbility)
        }
    }

    private val skillAdapter by lazy {
        SkillAdapter().apply {
            submitList(viewModel.information.value?.skill)
        }
    }

    @Inject
    lateinit var championInformationRepository: ChampionInformationRepository

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        onCreateItemRecyclerView()
        onCreateRuneRecyclerView()
        onCreateSpellRecyclerView()
        onCreateAbilityRecyclerView()
        onCreateSubAbilityRecyclerView()
        onCreateSkillRecyclerView()
        onCreateOnUniverse()

        return binding.root
    }

    override fun onCreateViewDataBinding() {
        super.onCreateViewDataBinding()
        binding.viewModel = viewModel
    }

    private fun onCreateItemRecyclerView() {
        with(binding.itemRecyclerView) {
            adapter = itemAdapter
        }
    }

    private fun onCreateRuneRecyclerView() {
        with(binding.runeRecyclerView) {
            adapter = runeAdapter
        }
    }

    private fun onCreateSpellRecyclerView() {
        with(binding.spellRecyclerView) {
            adapter = spellAdapter
        }
    }

    private fun onCreateAbilityRecyclerView() {
        with(binding.abilityRecyclerView) {
            adapter = abilityAdapter
        }
    }

    private fun onCreateSubAbilityRecyclerView() {
        with(binding.subAbilityRecyclerView) {
            adapter = subAbilityAdapter
        }
    }

    private fun onCreateSkillRecyclerView() {
        with(binding.skillRecyclerView) {
            adapter = skillAdapter
        }
    }

    private fun onCreateOnUniverse() {
        binding.setOnUniverse {
            findNavController().navigate(ChampionInformationFragmentDirections.actionChampionInformationFragmentToWebViewActivity(viewModel.champion.value!!.universeURL))
        }
    }
}