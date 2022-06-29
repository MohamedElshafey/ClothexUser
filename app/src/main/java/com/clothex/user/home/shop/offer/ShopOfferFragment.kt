package com.clothex.user.home.shop.offer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.R
import com.clothex.user.databinding.FragmentShopOfferBinding
import com.clothex.user.offer.OfferAdapter
import com.clothex.user.utils.addDivider
import org.koin.android.ext.android.inject

class ShopOfferFragment : Fragment() {

    companion object {
        fun newInstance(shopId: String): ShopOfferFragment {
            val args = Bundle()
            args.putString("shopId", shopId)
            val fragment = ShopOfferFragment()
            fragment.arguments = args
            return fragment
        }
    }

    lateinit var binding: FragmentShopOfferBinding
    private val viewModel: ShopOfferViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopOfferBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.logScreen(ShopOfferFragment::class.java.simpleName)
        requireArguments().getString("shopId")?.let { shopId ->
            viewModel.fetchOffers(shopId)
        }
        binding.progressBar.isVisible = true
        viewModel.offersLiveData.observe(viewLifecycleOwner) { voucherList ->
            binding.progressBar.isGone = true
            binding.offersRV.adapter = OfferAdapter(voucherList, { offer ->
                findNavController().navigate(R.id.offerDetailsFragment, bundleOf("offer" to offer))
            }, viewModel.isArabic())
        }
        binding.offersRV.addDivider()
    }

}