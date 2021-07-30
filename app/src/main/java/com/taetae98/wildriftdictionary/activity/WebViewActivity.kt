package com.taetae98.wildriftdictionary.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.navArgs
import androidx.navigation.ui.AppBarConfiguration
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.databinding.ActivityWebViewBinding
import com.taetae98.wildriftdictionary.databinding.BindingActivity
import com.taetae98.wildriftdictionary.viewmodel.WebViewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewActivity : BindingActivity<ActivityWebViewBinding>(R.layout.activity_web_view) {
    private val viewModel by viewModels<WebViewViewModel>()

    override val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.webViewFragment
            )
        )
    }

    private val args by navArgs<WebViewActivityArgs>()
    private val url by lazy { args.url }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateViewModel()
    }


    private fun onCreateViewModel() {
        viewModel.url.value = url
    }
}