package com.clothex.user.home.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isInvisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.clothex.data.domain.model.body.OrderBody
import com.clothex.data.domain.model.my_item.MyItem
import com.clothex.user.R
import com.clothex.user.data.my_items.MyItemGroup
import com.clothex.user.databinding.FragmentBookBinding
import com.clothex.user.dialog.MessageAlertDialog
import com.clothex.user.home.shop.contact.ContactsAdapter
import com.clothex.user.my_items.minimal.EditMinimalItemAdapter
import com.clothex.user.utils.setAllOnClickListener
import com.clothex.user.utils.setImageFromUrl
import com.clothex.user.utils.setRotationByLocale
import org.koin.android.ext.android.inject


/**
 * Created by Mohamed Elshafey on 09/12/2021.
 */
class BookFragment : Fragment() {

    lateinit var binding: FragmentBookBinding
    lateinit var myItemGroup: MyItemGroup
    private val mViewModel: BookViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    lateinit var adapter: EditMinimalItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        myItemGroup = BookFragmentArgs.fromBundle(requireArguments()).myItem
        binding.leadingIV.setRotationByLocale()
        binding.bookButton.isInvisible = myItemGroup.shop.hasBook.not()
        binding.contactsRV.isInvisible = myItemGroup.shop.hasBook
        binding.contactsRV.adapter = ContactsAdapter(myItemGroup.shop.socialMedias)
        with(myItemGroup) {
            binding.shopTitleTV.text = shop.getName(mViewModel.isArabic())
            binding.addressTV.text = branch.address?.getName(mViewModel.isArabic())
            setImageFromUrl(binding.shopLogoIV, shop.logo?.source)
        }

        val itemsList = ArrayList(myItemGroup.myItems)
        adapter = EditMinimalItemAdapter(itemsList) { deletedItem, list ->
            mViewModel.deleteMyItem(deletedItem.id) {
                itemsList.remove(deletedItem)
                adapter.notifyItemRemoved(list.indexOf(deletedItem))
                calculatePrices(itemsList)
            }
        }
        binding.itemsRV.adapter = adapter
        val simpleItemTouchCallback: ItemTouchHelper.SimpleCallback = object :
            ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val position = viewHolder.adapterPosition
                mViewModel.deleteMyItem(itemsList[position].id) {
                    Toast.makeText(requireContext(), "Deleted ${it.success}", Toast.LENGTH_SHORT)
                        .show()
                    itemsList.removeAt(position)
                    adapter.notifyItemRemoved(position)
                    calculatePrices(itemsList)
                }
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.itemsRV)
        calculatePrices(myItemGroup.myItems)
        binding.bookButton.setOnClickListener {
            val myItemsIds = myItemGroup.myItems.map { it.id }
            mViewModel.isLoginTemporaryLiveData.observe(viewLifecycleOwner, { isLoginTemporary ->
                if (isLoginTemporary.not()) {
                    mViewModel.createMyOrder(
                        OrderBody(
                            myItemsIds,
                            myItemGroup.branch.id,
                            myItemGroup.shop.id
                        )
                    ) { simpleResponse ->
                        if (simpleResponse?.success == true) {
                            findNavController().navigate(BookFragmentDirections.actionBookFragmentToRequestBookFragment())
                        } else MessageAlertDialog.showAlertDialog(
                            requireContext(),
                            getString(R.string.error),
                            getString(R.string.error_happened_message_title),
                            getString(R.string.done),
                            iconRes = R.drawable.ic_wrong_small,
                            positiveOnClickListener = {

                            }
                        )
                    }
                } else {
                    findNavController().navigate(BookFragmentDirections.actionBookFragmentToLoginFragment())
                }
            })
        }

        binding.shopGroup.setAllOnClickListener {
            findNavController().navigate(
                BookFragmentDirections.actionBookFragmentToShopDetailsFragment(
                    myItemGroup.shop.id
                )
            )
        }
        binding.actionBar.setOnClickListener { findNavController().navigateUp() }
    }

    override fun onResume() {
        super.onResume()
        mViewModel.getIsLoginUser()
    }

    private fun calculatePrices(items: List<MyItem>) {
        val totalPrice = items.map { it.product.price * it.quantity }.sum()
        getString(R.string.egp_price_format_float).let {
            binding.subtotalTV.text = String.format(it, totalPrice.toFloat())
            binding.discountTV.text = String.format(it, 0f)
            binding.totalTV.text = String.format(it, totalPrice.toFloat())
        }
    }

}