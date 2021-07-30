package com.taetae98.wildriftdictionary.dialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.databinding.BindingDialog
import com.taetae98.wildriftdictionary.databinding.DialogRuneBinding

class RuneDialog : BindingDialog<DialogRuneBinding>(R.layout.dialog_rune) {
    private val args by navArgs<RuneDialogArgs>()
    private val rune by lazy { args.rune }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateViewDataBinding() {
        super.onCreateViewDataBinding()
        binding.rune = rune
    }
}