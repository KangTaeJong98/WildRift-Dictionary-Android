package com.taetae98.wildriftdictionary.dialog

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.databinding.BindingDialog
import com.taetae98.wildriftdictionary.databinding.DialogSkillBinding

class SkillDialog : BindingDialog<DialogSkillBinding>(R.layout.dialog_skill) {
    private val args by navArgs<SkillDialogArgs>()
    private val skill by lazy { args.skill }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    override fun onCreateViewDataBinding() {
        super.onCreateViewDataBinding()
        binding.skill = skill
    }
}