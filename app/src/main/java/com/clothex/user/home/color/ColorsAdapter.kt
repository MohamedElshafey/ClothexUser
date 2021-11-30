package com.clothex.user.home.color

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.clothex.user.R
import com.clothex.user.databinding.AdapterItemColorBinding
import java.util.*


class ColorsAdapter(
    private val colors: List<String>,
    private val selectColorCallback: (String) -> Unit
) :
    RecyclerView.Adapter<ColorsAdapter.ViewHolder>() {

    private val selectedViews = LinkedList<View>()

    inner class ViewHolder(val binding: AdapterItemColorBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterItemColorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val background = holder.binding.colorView.background as GradientDrawable
        background.setColor(Color.parseColor(colors[holder.adapterPosition]))
        holder.binding.colorView.background = background
        holder.binding.root.setOnClickListener {
            selectedViews.forEach { it.setBackgroundResource(R.drawable.color_bg_shape_unselected) }
            selectedViews.remove(holder.binding.root)
            selectedViews.add(holder.binding.root)
            holder.binding.root.setBackgroundResource(R.drawable.color_bg_shape_selected)
            selectColorCallback.invoke(colors[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = colors.size


}