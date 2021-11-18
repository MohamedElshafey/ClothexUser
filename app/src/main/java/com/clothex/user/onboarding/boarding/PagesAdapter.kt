package com.clothex.user.onboarding.boarding

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.clothex.user.R
import com.clothex.user.databinding.AdapterItemOnboardingBinding
import com.clothex.user.onboarding.boarding.PagesAdapter.ViewHolder

/**
 * Created by Mohamed Elshafey on 18/11/2021.
 */
class PagesAdapter(
    private val list: List<OnBoardingModel>,
    private val clickCallback: (Int) -> Unit
) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            AdapterItemOnboardingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.adapterPosition], holder.adapterPosition == list.size - 1)

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(private val binding: AdapterItemOnboardingBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: OnBoardingModel, isLastItem: Boolean) {
            val context = binding.root.context
            binding.imageView.setImageResource(model.imageRes)
            binding.titleTV.text = context.getString(model.title)
            binding.descriptionTV.text = context.getString(model.description)
            val buttonTextRes = if (isLastItem) R.string.lets_start else R.string.next
            binding.bottomButton.text = binding.root.context.getString(buttonTextRes)
            binding.bottomButton.setOnClickListener { clickCallback.invoke(adapterPosition) }
            val buttonBackground = if (isLastItem) R.color.island_aqua else R.color.button_gray
            val backgroundDrawable = binding.bottomButton.background as GradientDrawable
            backgroundDrawable.setColor(ContextCompat.getColor(context, buttonBackground))
            binding.bottomButton.background = backgroundDrawable
            val buttonTextColor = if (isLastItem) R.color.white else R.color.black
            binding.bottomButton.setTextColor(ContextCompat.getColor(context, buttonTextColor))
        }
    }
}