package com.company.nond.home.presentation

import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.company.nond.BaseFragment
import com.company.nond.R
import com.company.nond.databinding.FragmentHomeBinding
import com.company.nond.utils.binding.viewBinding
import com.company.nond.utils.collectWhileStarted
import com.company.nond.utils.hideLoading
import com.company.nond.utils.showLoading
import com.company.nond.utils.showLongToast
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment(R.layout.fragment_home) {

    private val binding by viewBinding(FragmentHomeBinding::bind)

    @Inject
    lateinit var homeViewModel: HomeViewModel
    private val homePageItemsAdapter by lazy { HomePageItemsAdapter(::onItemClicked) }

    override fun setupView() {
        loadData()
        setupObservers()
        initRecyclerView()
    }

    private fun loadData() {
        homeViewModel.loadHomePageData()
    }

    private fun setupObservers() {
        with(homeViewModel) {
            isLoadingStateFlow.collectWhileStarted(viewLifecycleOwner) {
                if (it) showLoading() else hideLoading()
            }
            homePageDataList.collectWhileStarted(viewLifecycleOwner) {
                homePageItemsAdapter.submitList(it)
            }
            errorStateFlow.collectWhileStarted(viewLifecycleOwner) {
                showLongToast(it, requireContext())
            }
        }
    }

    private fun initRecyclerView() {
        binding.homePageItemsRv.apply {
            layoutManager = GridLayoutManager(requireContext(), 2)
            adapter = homePageItemsAdapter
        }
    }

    private fun onItemClicked(itemName: String, itemPrice: String, imageUrl: String) {
        findNavController().navigate(
            R.id.action_homeFragment_to_itemDetailsFragment,
            bundleOf(ITEM_NAME to itemName, ITEM_PRICE to itemPrice, IMAGE_URL to imageUrl)
        )
    }

    companion object {
        const val ITEM_PRICE = "itemPrice"
        const val ITEM_NAME = "itemName"
        const val IMAGE_URL = "imageUrl"
    }
}