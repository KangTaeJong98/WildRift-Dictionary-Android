package com.taetae98.wildriftdictionary.dialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.databinding.BindingDialog
import com.taetae98.wildriftdictionary.databinding.DialogSpellBinding

class SpellDialog : BindingDialog<DialogSpellBinding>(R.layout.dialog_spell) {
    private val args by navArgs<SpellDialogArgs>()
    private val spell by lazy { args.spell }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateViewDataBinding() {
        super.onCreateViewDataBinding()
        binding.spell = spell
    }
}