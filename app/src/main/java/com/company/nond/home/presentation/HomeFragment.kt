package com.company.nond.home.presentation

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.company.nond.BaseFragment
import com.company.nond.R
import com.company.nond.databinding.FragmentHomeBinding
import com.company.nond.utils.binding.viewBinding
import com.company.nond.utils.hideLoading
import com.company.nond.utils.showLoading
import com.company.nond.utils.showLongToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    @Inject
    lateinit var homeViewModel: HomeViewModel
    private lateinit var homePageItemsAdapter: HomePageItemsAdapter

    override fun setupView() {
        loadData()
        setupObservers()
        initRecyclerView()
    }

    private fun loadData() {
        homeViewModel.loadHomePageData()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            homeViewModel.isLoadingStateFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { isLoading ->
                    if (isLoading) showLoading() else hideLoading()
                }
        }

        lifecycleScope.launch {
            homeViewModel.homePageDataList
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { data ->
                    homePageItemsAdapter.submitList(data)
                }
        }

        lifecycleScope.launch {
            homeViewModel.errorStateFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collect { errorMessage ->
                    showLongToast(errorMessage, requireContext())
                }
        }
    }

    private fun initRecyclerView() {
        binding.homePageItemsRv.apply {
            homePageItemsAdapter = HomePageItemsAdapter()
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = homePageItemsAdapter
        }
    }

}