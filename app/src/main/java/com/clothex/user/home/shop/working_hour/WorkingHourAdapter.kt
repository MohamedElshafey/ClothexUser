package com.clothex.user.home.shop.working_hour

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clothex.data.domain.model.product.WorkingHour
import com.clothex.user.databinding.AdapterItemWorkingHourBinding

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class WorkingHourAdapter(private val list: List<WorkingHour>) :
    RecyclerView.Adapter<WorkingHourAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemWorkingHourBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.adapterPosition])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterItemWorkingHourBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(workingHour: WorkingHour) {
            binding.dayTV.text = workingHour.title
            binding.hoursTV.text = String.format("%s . %s", workingHour.from, workingHour.to)
        }
    }

}