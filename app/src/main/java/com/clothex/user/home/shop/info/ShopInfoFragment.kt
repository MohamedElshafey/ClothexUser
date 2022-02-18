package com.clothex.user.home.shop.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clothex.data.domain.model.shop.Shop
import com.clothex.user.R
import com.clothex.user.databinding.FragmentShopInfoBinding
import com.clothex.user.home.shop.contact.ContactsAdapter
import com.clothex.user.home.shop.working_hour.WorkingHourAdapter
import com.clothex.user.utils.setRotationByLocale

/**
 * Created by Mohamed Elshafey on 11/12/2021.
 */
class ShopInfoFragment : Fragment() {

    lateinit var binding: FragmentShopInfoBinding
    lateinit var shop: Shop
    private val viewModel = ShopInfoViewModel()

    companion object {
        fun newInstance(shop: Shop): ShopInfoFragment {
            val args = Bundle()
            args.putParcelable("shop", shop)
            val fragment = ShopInfoFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopInfoBinding.inflate(LayoutInflater.from(context), container, false)
        shop = arguments?.getParcelable("shop")!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.branchLeadingIconIV.setRotationByLocale()
        binding.branchCountTV.text = "+${shop.branches.size}"
        binding.branchSuffixTV.text = getString(R.string.more_branches)
        val selectedBranch = shop.branches[0]
        val workingHours = selectedBranch.workingHours
        binding.workingHoursRV.adapter =
            workingHours?.let { WorkingHourAdapter(it, viewModel.isArabic()) }
        val contacts = shop.socialMedias
        binding.contactsRV.adapter = ContactsAdapter(contacts)
        binding.addressTV.text =
            context?.getString(R.string.nearest_branch) + ": ${
                shop.branches[0].address?.getName(
                    viewModel.isArabic()
                )
            }"
        binding.aboutTV.text = shop.about
    }
}