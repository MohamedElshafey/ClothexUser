package com.clothex.user.offer.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.data.domain.model.offer.Offer
import com.clothex.user.R
import com.clothex.user.databinding.FragmentOfferDetailsBinding
import com.clothex.user.utils.DateUtil.toLocalTimeZone
import com.clothex.user.voucher.shop_with_branch.VoucherShopAdapter


/**
 * Created by Mohamed Elshafey on 21/12/2021.
 */
class OfferDetailsFragment : Fragment() {
    private lateinit var mViewModel: OfferDetailsViewModel
    private var _binding: FragmentOfferDetailsBinding? = null
    private val binding get() = _binding!!
    lateinit var offer: Offer
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        offer = OfferDetailsFragmentArgs.fromBundle(requireArguments()).offer
        mViewModel = OfferDetailsViewModel(offer)
        _binding = FragmentOfferDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = mViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val expireString = String.format(
            requireContext().getString(R.string.expired_in),
            offer.expiryDate.toLocalTimeZone(requireContext())
        )
        binding.expireTV.text = expireString
        binding.actionBar.setOnClickListener { findNavController().navigateUp() }
        binding.shopsRecyclerView.adapter =
            VoucherShopAdapter(mViewModel.offer.shops, mViewModel.isArabic())
    }
}