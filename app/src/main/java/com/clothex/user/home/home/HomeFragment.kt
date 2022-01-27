package com.clothex.user.home.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clothex.user.R
import com.clothex.user.databinding.FragmentHomeBinding
import com.clothex.user.home.product.ProductAdapter
import com.clothex.user.home.shop.ShopAdapter
import com.clothex.user.utils.KEY_DISMISS
import com.clothex.user.utils.setAllOnClickListener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.android.ext.android.inject


class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by inject()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        viewModel.checkFirstTimeOpen()
        viewModel.getSelectedLocation()
        viewModel.savedLocationLiveData.observe(viewLifecycleOwner, {
            binding.locationValueTV.text =
                it?.title ?: context?.getString(R.string.select_location_to_search)
        })
        viewModel.isFirstTimeOpenLiveData.observe(viewLifecycleOwner, { isFirstTime ->
            if (isFirstTime) {
                findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToLoginFragment())
            } else {
                FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
                    if (!task.isSuccessful) {
                        return@OnCompleteListener
                    }
                    val token = task.result
                    viewModel.updateFCMToken(token)
                })
                viewModel.fetchHome()
            }
        })

        viewModel.failureLiveData.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), "Error, $it", Toast.LENGTH_SHORT).show()
        })

        viewModel.productLiveData.observe(viewLifecycleOwner, {
            binding.swipeRefresh.isRefreshing = false
            binding.productRV.adapter = ProductAdapter(it) { prod ->
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToProductDetailsFragment(prod.id)
                )
            }
        })

        viewModel.shopLiveData.observe(viewLifecycleOwner, {
            binding.shopsRV.adapter = ShopAdapter(it) {
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToShopDetailsFragment(it.id)
                )
            }
        })

        val layoutManager = object : LinearLayoutManager(requireContext()) {
            override fun checkLayoutParams(lp: RecyclerView.LayoutParams): Boolean {
                lp.width = (width * 0.7f).toInt()
                return true
            }
        }

        layoutManager.orientation = LinearLayoutManager.HORIZONTAL
        binding.shopsRV.layoutManager = layoutManager

        binding.notificationIV.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNotificationFragment())
        }

        binding.locationGroup.setAllOnClickListener {
            findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Boolean>(
                KEY_DISMISS
            )?.observe(viewLifecycleOwner, {
                it?.let {
                    viewModel.getSelectedLocation()
                }
            })
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToSelectLocationBottomSheet())
        }

        binding.searchBar.apply {
            searchET.isClickable = false
            searchET.isFocusable = false
            searchBarContainer.setOnClickListener { openSearchFragment() }
            searchET.setOnClickListener { openSearchFragment() }
            menu.setOnClickListener { openSearchFragment() }
        }

        binding.arrivalSeeAllTV.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToSearchProductFragment(true)
            )
        }

        binding.shopSeeAllTV.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToSearchProductFragment(false)
            )
        }

        binding.voucherButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToVoucherFragment())
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchHome()
        }

    }

    private fun openSearchFragment() {
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToSearchProductFragment(true)
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}