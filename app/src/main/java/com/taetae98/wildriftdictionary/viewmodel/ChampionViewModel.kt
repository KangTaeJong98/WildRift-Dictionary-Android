package com.taetae98.wildriftdictionary.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.taetae98.wildriftdictionary.dto.Champion
import com.taetae98.wildriftdictionary.dto.ChampionInformation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChampionViewModel @Inject constructor(
    stateHandle: SavedStateHandle
) : ViewModel() {
    val champion = stateHandle.getLiveData<Champion>("CHAMPION")
    val information = stateHandle.getLiveData<ChampionInformation>("INFORMATION")
}