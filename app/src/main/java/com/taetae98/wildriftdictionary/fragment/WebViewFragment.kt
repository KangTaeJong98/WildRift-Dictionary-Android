package com.taetae98.wildriftdictionary.fragment

import androidx.fragment.app.activityViewModels
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.databinding.BindingFragment
import com.taetae98.wildriftdictionary.databinding.FragmentWebViewBinding
import com.taetae98.wildriftdictionary.viewmodel.WebViewViewModel

class WebViewFragment : BindingFragment<FragmentWebViewBinding>(R.layout.fragment_web_view) {
    private val viewModel by activityViewModels<WebViewViewModel>()

    override fun onCreateViewDataBinding() {
        super.onCreateViewDataBinding()
        binding.viewModel = viewModel
    }
}