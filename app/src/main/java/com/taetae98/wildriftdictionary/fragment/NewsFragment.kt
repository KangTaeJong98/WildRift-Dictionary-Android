package com.taetae98.wildriftdictionary.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.taetae98.wildriftdictionary.R
import com.taetae98.wildriftdictionary.adapter.NewsAdapter
import com.taetae98.wildriftdictionary.databinding.BindingFragment
import com.taetae98.wildriftdictionary.databinding.FragmentNewsBinding
import com.taetae98.wildriftdictionary.repository.NewRepository
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : BindingFragment<FragmentNewsBinding>(R.layout.fragment_news) {
    @Inject
    lateinit var newRepository: NewRepository

    private val newsAdapter by lazy {
        NewsAdapter().apply {
            onNewsClickListener = {
                findNavController().navigate(
                    NewsFragmentDirections.actionNewsFragmentToWebViewActivity(
                        it.url
                    )
                )
            }

            submitList(newRepository.findAll())
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
    }
}