package com.clothex.user.home.shop.contact

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.clothex.data.domain.model.SocialMedia
import com.clothex.user.databinding.AdapterItemContactBinding
import com.clothex.user.utils.setImageFromUrl

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class ContactsAdapter(private val list: List<SocialMedia>) :
    RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(
        AdapterItemContactBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[holder.adapterPosition])

    override fun getItemCount(): Int = list.size

    inner class ViewHolder(val binding: AdapterItemContactBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(socialMedia: SocialMedia) {
            setImageFromUrl(binding.iconIV, socialMedia.social.icon)
            binding.iconIV.setOnClickListener {
                val appendUrl = socialMedia.social.appendUrl
                val value = if(appendUrl.isNullOrEmpty()) socialMedia.value
                else socialMedia.social.appendUrl + socialMedia.value
                    val implicitIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(value)
                )
                try {
                    it.context.startActivity(implicitIntent)
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(
                        it.context,
                        "${socialMedia.social.title} App not found!",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            }
        }
    }

}