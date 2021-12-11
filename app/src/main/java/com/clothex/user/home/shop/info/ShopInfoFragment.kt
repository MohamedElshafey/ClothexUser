package com.clothex.user.home.shop.info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clothex.user.data.Shop
import com.clothex.user.data.workingHourList
import com.clothex.user.databinding.FragmentShopInfoBinding
import com.clothex.user.home.shop.contact.ContactsAdapter
import com.clothex.user.home.shop.details.ShopDetailsViewModel
import com.clothex.user.home.shop.working_hour.WorkingHourAdapter

/**
 * Created by Mohamed Elshafey on 11/12/2021.
 */
class ShopInfoFragment : Fragment() {

    lateinit var binding: FragmentShopInfoBinding
    lateinit var mViewModel: ShopDetailsViewModel
    lateinit var shop: Shop

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
        mViewModel = ShopDetailsViewModel(shop = shop)
        binding.viewModel = mViewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.branchCountTV.text = "+10"
        binding.branchSuffixTV.text = "more branches"
        binding.workingHoursRV.adapter = WorkingHourAdapter(workingHourList)
        binding.contactsRV.adapter = ContactsAdapter(listOf(""))
    }

}