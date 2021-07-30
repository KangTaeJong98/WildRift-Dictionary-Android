package com.taetae98.wildriftdictionary.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.navArgs
import androidx.navigation.ui.AppBarConfiguration
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.databinding.ActivityChampionBinding
import com.taetae98.wildriftdictionary.databinding.BindingActivity
import com.taetae98.wildriftdictionary.repository.ChampionInformationRepository
import com.taetae98.wildriftdictionary.viewmodel.ChampionViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ChampionActivity : BindingActivity<ActivityChampionBinding>(R.layout.activity_champion) {
    private val viewModel by viewModels<ChampionViewModel>()

    private val args by navArgs<ChampionActivityArgs>()
    private val champion by lazy { args.champion }
    private val information by lazy { championInformationRepository.findById(champion.id) }

    @Inject
    lateinit var championInformationRepository: ChampionInformationRepository

    override val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(R.id.championFragment)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.champion.value = champion
        viewModel.information.value = information
    }
}