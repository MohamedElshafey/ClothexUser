package com.clothex.user.my_items.items

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.R
import com.clothex.user.data.my_items.MyItemGroup
import com.clothex.user.databinding.FragmentMyItemsBinding
import com.clothex.user.dialog.MessageAlertDialog
import org.koin.android.ext.android.inject

/**
 * Created by Mohamed Elshafey on 08/12/2021.
 */

class MyItemsFragment : Fragment(), MyItemCallback {

    lateinit var binding: FragmentMyItemsBinding
    private val mViewModel: MyItemsViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMyItemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewModel.fetchMyItems()
        binding.progressBar.isVisible = true
        mViewModel.mutableMyItemsLiveData.observe(viewLifecycleOwner, { myItems ->
            binding.progressBar.isGone = true
            val grouped = myItems?.groupBy {
                it.branch
            }
            val groupList = mutableListOf<MyItemGroup>()
            grouped?.forEach {
                groupList.add(MyItemGroup(it.value.first().shop, it.key, it.value))
            }
            binding.recyclerView.adapter = MyItemsAdapter(groupList, this)

                if (myItems.isNullOrEmpty()) {
                    binding.messageContainer.apply {
                        messageIV.setImageResource(R.drawable.ic_no_items_found)
                        messageTitleTV.setText(R.string.no_my_items_message)
                        messageSubTitleTV.setText(R.string.no_my_items_description)
                    }
                }
            binding.messageContainer.container.isGone = myItems.isNullOrEmpty().not()
        })

    }

    override fun onItemClicked(myItemGroup: MyItemGroup) {
        val bundle = bundleOf("myItem" to myItemGroup)
        findNavController().navigate(R.id.bookFragment, bundle)
    }

    override fun deleteMyItemGroup(myItemGroup: MyItemGroup) {
        MessageAlertDialog.showAlertDialog(
            requireContext(),
            "Are you sure?",
            "you want to delete this group of items? if you delete it you will lose you items in it.",
            "Cancel",
            "Delete",
            iconRes = R.drawable.ic_dialog_delete,
            negativeOnClickListener = {
                mViewModel.deleteMyItemGroup(myItemGroup) { response ->
                    var message = "Error happened, please try again!"
                    if (response != null) {
                        if (response.success) {
                            message = "Deleted successfully!"
                            mViewModel.fetchMyItems()
                        } else {
                            response.message?.let { message = it }
                        }
                    }
                    Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
                }
            }
        )
    }
}