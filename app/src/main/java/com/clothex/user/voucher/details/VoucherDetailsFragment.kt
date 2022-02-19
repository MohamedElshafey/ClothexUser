package com.clothex.user.voucher.details

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.data.domain.model.voucher.Voucher
import com.clothex.user.R
import com.clothex.user.databinding.FragmentVoucherDetailsBinding
import com.clothex.user.utils.DateUtil.toLocalTimeZone
import com.clothex.user.voucher.shop_with_branch.VoucherShopAdapter
import org.koin.android.ext.android.inject
import retrofit2.HttpException


/**
 * Created by Mohamed Elshafey on 21/12/2021.
 */
class VoucherDetailsFragment : Fragment() {
    private lateinit var mViewModel: VoucherDetailsViewModel
    val redeemViewModel: RedeemVoucherViewModel by inject()
    private var _binding: FragmentVoucherDetailsBinding? = null
    private val binding get() = _binding!!
    lateinit var voucher: Voucher
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        voucher = VoucherDetailsFragmentArgs.fromBundle(requireArguments()).voucher
        mViewModel = VoucherDetailsViewModel(voucher)
        _binding = FragmentVoucherDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = mViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val expireString = String.format(
            requireContext().getString(R.string.expired_in),
            voucher.expiryDate.toLocalTimeZone(requireContext() )
        )
        binding.expireTV.text = expireString
        binding.actionBar.setOnClickListener { findNavController().navigateUp() }
        binding.shopsRecyclerView.adapter =
            VoucherShopAdapter(mViewModel.voucher.shops, mViewModel.isArabic())
        binding.useVoucherButton.setOnClickListener {
            redeemViewModel.redeem(mViewModel.voucher.id) { result ->
                result.getOrNull()?.let {
                    val decodedString: ByteArray =
                        Base64.decode(it.qrCode.split(",")[1], Base64.DEFAULT)
                    val bitmap =
                        BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
                    findNavController().navigate(
                        VoucherDetailsFragmentDirections.actionVoucherDetailsFragmentToQrBottomSheet(
                            bitmap
                        )
                    )
                }
                result.exceptionOrNull()?.let {
                    Toast.makeText(
                        requireContext(),
                        (it as HttpException).message(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}