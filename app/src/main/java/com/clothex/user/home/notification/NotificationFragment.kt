package com.clothex.user.home.notification

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.clothex.data.domain.model.notification.Notification
import com.clothex.user.databinding.FragmentNotificationsBinding
import com.clothex.user.home.notification.adapter.NotificationAdapter
import com.clothex.user.home.notification.adapter.NotificationCallback
import org.koin.android.ext.android.inject


/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class NotificationFragment : Fragment(), NotificationCallback {
    private val notificationViewModel: NotificationViewModel by inject()
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private val adapter = NotificationAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.progressBar.isGone = false
        notificationViewModel.fetchNotifications()
        notificationViewModel.notificationLiveData.observe(viewLifecycleOwner, { result ->
            result.getOrNull()?.let { list ->
                adapter.setData(list)
            }
            binding.progressBar.isGone = true
        })
        val dividerItemDecoration = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        binding.notificationRV.addItemDecoration(dividerItemDecoration)
        binding.notificationRV.adapter = adapter
        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(notification: Notification) {
        try {
            findNavController().navigate(Uri.parse(notification.action))
        } catch (e: IllegalArgumentException) {
        }
        notificationViewModel.readNotification(notificationId = notification.id) {

        }
    }

}