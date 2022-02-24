package com.clothex.user.home.image_viewer

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import com.clothex.data.domain.model.product.Media
import com.clothex.user.R
import com.clothex.user.databinding.DialogImageViewerBinding
import com.clothex.user.utils.setRotationByLocale

class ImageViewerDialog : DialogFragment() {

    lateinit var binding: DialogImageViewerBinding

    companion object {
        fun newInstance(images: Array<Media>, selectedIndex: Int): ImageViewerDialog {
            val dialog = ImageViewerDialog()
            val args = Bundle()
            args.putParcelableArray("images", images)
            args.putInt("selectedIndex", selectedIndex)
            dialog.arguments = args
            return dialog
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.Dialog_NoTitle);
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setGravity(Gravity.CENTER)
        dialog?.window?.setLayout(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DialogImageViewerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backIV.setRotationByLocale()
        binding.backIV.setOnClickListener {
            dismiss()
        }
        val mediaArray = ImageViewerDialogArgs.fromBundle(requireArguments()).images
        val selectedIndex = ImageViewerDialogArgs.fromBundle(requireArguments()).selectedIndex
        val adapter = ImageViewerAdapter(mediaArray.toList())
        binding.imageViewPager.adapter = adapter
        binding.imageViewPager.currentItem = selectedIndex
        binding.indicator.setViewPager(binding.imageViewPager)
    }

}