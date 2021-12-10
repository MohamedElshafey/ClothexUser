package com.clothex.user.home.book

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.clothex.user.data.my_items.MinimalProduct
import com.clothex.user.databinding.FragmentBookBinding
import com.clothex.user.my_items.minimal.EditMinimalItemAdapter
import com.clothex.user.utils.setImageFromUrl


/**
 * Created by Mohamed Elshafey on 09/12/2021.
 */
class BookFragment : Fragment() {

    lateinit var binding: FragmentBookBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val myItem = BookFragmentArgs.fromBundle(requireArguments()).myItem
        with(myItem.shop) {
            binding.shopTitleTV.text = name
            binding.addressTV.text = addressName
            setImageFromUrl(binding.logoIV, logoUrl)
        }

        val itemsList = ArrayList(myItem.myItems)
        val adapter = EditMinimalItemAdapter(itemsList) { _, list ->
            calculatePrices(list)
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
                itemsList.removeAt(position)
                adapter.notifyItemRemoved(position)
                calculatePrices(itemsList)
            }
        }
        val itemTouchHelper = ItemTouchHelper(simpleItemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.itemsRV)


        calculatePrices(myItem.myItems)
        binding.bookButton.setOnClickListener {
            findNavController().navigate(BookFragmentDirections.actionBookFragmentToRequestBookFragment())
        }
    }

    private fun calculatePrices(items: List<MinimalProduct>) {
        val totalPrice = items.map { it.price * it.quantity }.sum()
        binding.subtotalTV.text = String.format("EGP %.02f", totalPrice.toFloat())
        binding.discountTV.text = String.format("EGP %.02f", 0f)
        binding.totalTV.text = String.format("EGP %.02f", totalPrice.toFloat())

    }

}