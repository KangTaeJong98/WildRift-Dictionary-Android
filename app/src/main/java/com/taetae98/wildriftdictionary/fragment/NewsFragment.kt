package com.taetae98.wildriftdictionary.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.NewsAdapter
import com.taetae98.wildriftdictionary.databinding.BindingFragment
import com.taetae98.wildriftdictionary.databinding.FragmentNewsBinding
import com.taetae98.wildriftdictionary.viewmodel.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NewsFragment : BindingFragment<FragmentNewsBinding>(R.layout.fragment_news) {
    private val viewModel by viewModels<NewsViewModel>()
    private val newsAdapter by lazy {
        NewsAdapter().apply {
            onNewsClickListener = {
                findNavController().navigate(
                    NewsFragmentDirections.actionNewsFragmentToWebViewActivity(
                        requireContext().getString(R.string.news), it.url
                    )
                )
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        super.onCreateView(inflater, container, savedInstanceState)
        onCreateRecyclerView()

        return binding.root
    }

    private fun onCreateRecyclerView() {
        with(binding.recyclerView) {
            adapter = newsAdapter
        }

        lifecycleScope.launchWhenResumed {
            viewModel.data.collect {
                newsAdapter.submitList(it)
            }
        }
    }
}