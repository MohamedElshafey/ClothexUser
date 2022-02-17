package com.clothex.user.my_items.orders.details

import android.content.Intent
import android.graphics.drawable.GradientDrawable
import android.net.Uri
import android.os.Bundle
import android.os.CountDownTimer
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.clothex.user.R
import com.clothex.user.databinding.FragmentOrderDetailsBinding
import com.clothex.user.my_items.minimal.MinimalItemAdapter
import com.clothex.user.utils.CustomTypefaceSpan
import com.clothex.user.utils.DateUtil
import com.clothex.user.utils.DateUtil.toLocalTimeZone
import org.koin.android.ext.android.inject
import java.util.*

class OrderDetailsFragment : Fragment() {

    private val viewModel: OrderDetailsViewModel by inject()
    lateinit var binding: FragmentOrderDetailsBinding

    lateinit var orderId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderId = OrderDetailsFragmentArgs.fromBundle(requireArguments()).orderId
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOrderDetailsBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchOrderDetails(orderId)
        binding.actionBar.setOnClickListener { findNavController().navigateUp() }
        viewModel.orderMutableLiveData.observe(viewLifecycleOwner, { result ->
            val context = context ?: return@observe
            result.getOrNull()?.let { order ->
                binding.itemsRV.adapter = MinimalItemAdapter(order.myItems, order.bookedItems)
                binding.orderIdTV.text =
                    String.format(context.getString(R.string.order), order.orderId)
                binding.placedDateTV.text =
                    String.format(
                        context.getString(R.string.placed_on),
                        order.placedOn?.toLocalTimeZone(context)
                    )
                val backgroundDrawable = binding.statusTV.background as GradientDrawable
                order.state.let {
                    binding.statusTV.text = context.getString(it.title)
                    binding.statusTV.setTextColor(it.contentColor)
                    backgroundDrawable.setColor(it.backgroundColor)
                }
                binding.statusTV.background = backgroundDrawable
                binding.bookedItemsTV.text = order.bookedItems.size.toString()
                val totalPrice = order.myItems.filter { it.id in order.bookedItems }
                    .map { it.product.price * it.quantity }.sum()
                binding.totalTV.text = totalPrice.toString()
                if (order.endTime != null && order.orderTimeStamp != null) {
                    binding.orderValidContainer.visibility = View.VISIBLE
                    binding.directionButton.visibility = View.VISIBLE
                    val diffTimeStamp =
                        DateUtil.getDifferenceTimeStamp(context, order.endTime!!) ?: 0
                    if (diffTimeStamp > 0) {
                        object : CountDownTimer(diffTimeStamp, 1000L) {
                            override fun onTick(millisUntilFinished: Long) {
                                val orderValidMsg = context.getString(R.string.order_valid_msg)

                                val seconds = (millisUntilFinished / 1000).toInt() % 60
                                val minutes = (millisUntilFinished / (1000 * 60) % 60)
                                val hours = (millisUntilFinished / (1000 * 60 * 60) % 24)

                                val hoursInString =
                                    String.format("%02d:%02d:%02d", hours, minutes, seconds)
                                val validText = String.format(orderValidMsg, hoursInString)
                                val fontBold =
                                    ResourcesCompat.getFont(
                                        context,
                                        R.font.ibm_plex_sans_arabic_bold
                                    )
                                val timeStartIndex = validText.indexOf(hoursInString)
                                val timeEndIndex = timeStartIndex + hoursInString.length
                                val spannableStringBuilder = SpannableStringBuilder(validText)
                                spannableStringBuilder.setSpan(
                                    CustomTypefaceSpan("", fontBold),
                                    timeStartIndex,
                                    timeEndIndex,
                                    Spanned.SPAN_EXCLUSIVE_INCLUSIVE
                                )
                                binding.orderValidTV.text = spannableStringBuilder
                            }

                            override fun onFinish() {
                                binding.orderValidTV.setText(R.string.order_run_out_of_time)
                            }
                        }.start()
                    } else {
                        binding.orderValidTV.setText(R.string.order_run_out_of_time)
                    }
                } else {
                    binding.orderValidContainer.visibility = View.GONE
                    binding.directionButton.visibility = View.GONE
                }
                binding.directionButton.setOnClickListener {
                    order.branch.address?.location?.let { location ->
                        if (location.coordinates.size == 2) {
                            val uri = String.format(
                                Locale.ENGLISH,
                                "http://maps.google.com/maps?q=loc:%f,%f",
                                location.coordinates[1], location.coordinates[0]
                            )
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
                            startActivity(intent)
                        }
                    }
                }
            }
            result?.exceptionOrNull()?.let {

            }
        })
    }

}