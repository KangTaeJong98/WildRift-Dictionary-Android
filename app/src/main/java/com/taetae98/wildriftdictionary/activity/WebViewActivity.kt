package com.taetae98.wildriftdictionary.activity

import androidx.navigation.navArgs
import androidx.navigation.ui.AppBarConfiguration
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.databinding.ActivityWebViewBinding
import com.taetae98.wildriftdictionary.databinding.BindingActivity

class WebViewActivity : BindingActivity<ActivityWebViewBinding>(R.layout.activity_web_view) {
    override val appBarConfiguration by lazy {
        AppBarConfiguration(
            setOf(
                R.id.webViewFragment
            )
        )
    }

    private val args by navArgs<WebViewActivityArgs>()

}