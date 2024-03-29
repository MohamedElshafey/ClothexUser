package com.clothex.user.home.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.distinctUntilChanged
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clothex.user.R
import com.clothex.user.databinding.FragmentHomeBinding
import com.clothex.user.home.categories.style.DepartmentEnum
import com.clothex.user.home.product.ProductAdapter
import com.clothex.user.home.shop.ShopAdapter
import com.clothex.user.log.MainLogEvents
import com.clothex.user.utils.KEY_DISMISS
import com.clothex.user.utils.setAllOnClickListener
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.android.ext.android.inject


class HomeFragment : Fragment(), (String) -> Unit {

    private val viewModel: HomeViewModel by inject()
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSelectedLocation()
        viewModel.logScreen(HomeFragment::class.java.simpleName)
        viewModel.checkFirstTimeOpen()
        viewModel.savedLocationLiveData.observe(viewLifecycleOwner) {
            binding.locationValueTV.text =
                it?.title ?: context?.getString(R.string.select_location_to_search)
        }
        binding.homeGroup.isGone = true
        viewModel.isFirstTimeOpenLiveData.distinctUntilChanged()
            .observe(viewLifecycleOwner) { isFirstTime ->
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
                    viewModel.fetchHome(this)
                }
            }

        viewModel.productLiveData.distinctUntilChanged().observe(viewLifecycleOwner) { products ->
            binding.productRV.adapter = ProductAdapter(products) { prod ->
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToProductDetailsFragment(prod.id)
                )
            }
            if (products.isNullOrEmpty()) {
                binding.messageContainer.apply {
                    messageIV.setImageResource(R.drawable.ic_shirt_error)
                    messageTitleTV.setText(R.string.error_title)
                    messageSubTitleTV.setText(R.string.swipe_error_message)
                }
            }
            with(products.isNullOrEmpty()) {
                binding.homeGroup.isGone = this
                binding.messageContainer.container.isVisible = this
            }
        }

        viewModel.shopLiveData.distinctUntilChanged().observe(viewLifecycleOwner) {
            binding.shopsRV.adapter = ShopAdapter(it) {
                findNavController().navigate(
                    HomeFragmentDirections.actionNavigationHomeToShopDetailsFragment(it.id)
                )
            }
        }

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
            )?.observe(viewLifecycleOwner) {
                it?.let {
                    viewModel.getSelectedLocation()
                }
            }
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

        binding.offerButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToOfferFragment())
        }

        binding.womenCard.setOnClickListener {
            /*findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToNavigationCategories(
                    DepartmentEnum.WOMEN
                )
            )*/
            openSelectType(DepartmentEnum.WOMEN)
        }

        binding.menCard.setOnClickListener {
            /*findNavController().navigate(
                HomeFragmentDirections.actionNavigationHomeToNavigationCategories(
                    DepartmentEnum.MEN
                )
            )*/
            openSelectType(DepartmentEnum.MEN)
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchHome(this)
        }

        viewModel.loadingLiveData.observe(viewLifecycleOwner) { isLoading ->
            if (isLoading) {
                if (viewModel.productLiveData.value.isNullOrEmpty()) {
                    binding.shimmerFrame.root.isVisible = true
                } else {
                    binding.progressBar.isVisible = true
                }
            } else {
                binding.shimmerFrame.root.isVisible = false
                binding.progressBar.isVisible = false
            }
            binding.homeGroup.isVisible = isLoading.not()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun openSelectType(departmentEnum: DepartmentEnum) {
        viewModel.logEvent(MainLogEvents.SelectDepartment(departmentEnum))
        val departmentsList = viewModel.departmentListLiveData.value?.getOrNull()
        val selectedDepartment =
            departmentsList?.find { it.title.equals(departmentEnum.value, true) }
        val departmentId = selectedDepartment?.id
        val types = selectedDepartment?.types
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToSelectTypeFragment(
                departmentId ?: "",
                departmentEnum,
                types?.toTypedArray() ?: arrayOf()
            )
        )
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

    override fun invoke(throwable: String) {
        activity?.runOnUiThread {
            Toast.makeText(
                requireContext(),
                getString(R.string.network_error_message),
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}