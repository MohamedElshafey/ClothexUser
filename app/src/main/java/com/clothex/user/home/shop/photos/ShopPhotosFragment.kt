package com.clothex.user.home.shop.photos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.clothex.data.domain.model.product.Media
import com.clothex.data.domain.model.shop.Shop
import com.clothex.user.databinding.FragmentShopPhotoBinding
import com.clothex.user.home.image.ImageSelectedListener
import com.clothex.user.home.image_viewer.ImageViewerDialog

/**
 * Created by Mohamed Elshafey on 11/12/2021.
 */
class ShopPhotosFragment : Fragment() {

    lateinit var binding: FragmentShopPhotoBinding
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
        shop = requireArguments().getParcelable("shop")!!
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mediaList = ArrayList<Media>()
        shop.branches?.forEach { branch ->
            if (branch.insidePhotos != null) {
                mediaList.addAll(branch.insidePhotos!!)
            }
            if (branch.outsidePhotos != null) {
                mediaList.addAll(branch.outsidePhotos!!)
            }
        }
        binding.recyclerView.adapter = ShopPhotosAdapter(mediaList, object : ImageSelectedListener {
            override fun onImageSelected(mediaList: List<Media>, selectedIndex: Int) {
                val dialog = ImageViewerDialog.newInstance(mediaList.toTypedArray(), selectedIndex)
                dialog.show(childFragmentManager, null)
            }
        })
    }

}