package com.clothex.user.home.shop.branch

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import com.clothex.data.domain.model.product.Branch
import com.clothex.user.customview.DefaultBottomSheet
import com.clothex.user.databinding.SelectBranchBottomSheetBinding
import com.clothex.user.utils.KEY_DISMISS

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class SelectBranchBottomSheet : DefaultBottomSheet(), SelectBranchCallback {
    lateinit var binding: SelectBranchBottomSheetBinding
    private val viewModel = SelectBranchViewModel()

    companion object {
        private var selectBranchCallback: SelectBranchCallback? = null
        fun newInstance(
            branches: List<Branch>,
            selectedIndex: Int,
            selectBranchCallback: SelectBranchCallback?
        ): SelectBranchBottomSheet {
            this.selectBranchCallback = selectBranchCallback
            val bottomSheet = SelectBranchBottomSheet()
            bottomSheet.arguments = bundleOf("branches" to branches, "index" to selectedIndex)
            return bottomSheet
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SelectBranchBottomSheetBinding.inflate(
            LayoutInflater.from(context),
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val selectedIndex = arguments?.getInt("index") ?: 0
        val branches = arguments?.getParcelableArrayList<Branch>("branches")!!
        binding.branchesRV.adapter = SelectBranchAdapter(
            branches, selectedIndex, this, viewModel.isArabic()
        )
    }

    override fun onDismiss(dialog: DialogInterface) {
        super.onDismiss(dialog)
        findNavController().previousBackStackEntry?.savedStateHandle?.set(KEY_DISMISS, true)
    }

    override fun onBranchSelected(branch: Branch) {
        selectBranchCallback?.onBranchSelected(branch)
        dismiss()
    }
}