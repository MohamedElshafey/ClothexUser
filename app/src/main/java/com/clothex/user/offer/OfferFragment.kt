package com.clothex.user.offer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.data.domain.model.offer.Offer
import com.clothex.user.databinding.FragmentOfferBinding
import com.clothex.user.utils.addDivider
import org.koin.android.ext.android.inject

/**
 * Created by Mohamed Elshafey on 10/12/2021.
 */
class OfferFragment : Fragment(), (Offer) -> Unit {

    lateinit var binding: FragmentOfferBinding
    private val viewModel: OfferViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOfferBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.logScreen(OfferFragment::class.java.simpleName)
        viewModel.fetchOffers()
        binding.progressBar.isVisible = true
        viewModel.vouchersLiveData.observe(viewLifecycleOwner) { voucherList ->
            binding.progressBar.isGone = true
            binding.offersRV.adapter = OfferAdapter(voucherList, this, viewModel.isArabic())
        }
        binding.offersRV.addDivider()
        binding.actionBar.setOnClickListener { findNavController().navigateUp() }
    }

    override fun invoke(offer: Offer) {
        findNavController().navigate(
            OfferFragmentDirections.actionOfferFragmentToOfferDetailsFragment(offer)
        )
    }

}