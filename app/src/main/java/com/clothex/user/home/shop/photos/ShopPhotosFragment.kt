package com.clothex.user.home.shop.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clothex.user.data.Shop
import com.clothex.user.databinding.FragmentShopPhotoBinding
import com.clothex.user.home.shop.details.ShopDetailsViewModel

/**
 * Created by Mohamed Elshafey on 11/12/2021.
 */
class ShopPhotosFragment : Fragment() {

    lateinit var binding: FragmentShopPhotoBinding
    lateinit var mViewModel: ShopDetailsViewModel
    lateinit var shop: Shop

    companion object {
        fun newInstance(shop: Shop): ShopPhotosFragment {
            val args = Bundle()
            args.putParcelable("shop", shop)
            val fragment = ShopPhotosFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopPhotoBinding.inflate(LayoutInflater.from(context), container, false)
        shop = arguments?.getParcelable("shop")!!
        mViewModel = ShopDetailsViewModel(shop = shop)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = ShopPhotosAdapter(mViewModel.shopPhotos)
    }

}