package com.taetae98.wildriftdictionary.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    stateHandle: SavedStateHandle
) : ViewModel() {
    val input by lazy { stateHandle.getLiveData("INPUT", "") }
}