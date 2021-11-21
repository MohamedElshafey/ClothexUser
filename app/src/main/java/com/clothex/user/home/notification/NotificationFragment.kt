package com.clothex.user.home.notification

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.clothex.user.data.notificationList
import com.clothex.user.databinding.FragmentNotificationsBinding
import com.clothex.user.home.notification.adapter.NotificationAdapter

/**
 * Created by Mohamed Elshafey on 21/11/2021.
 */
class NotificationFragment : Fragment() {
    private lateinit var notificationViewModel: NotificationViewModel
    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        notificationViewModel = ViewModelProvider(this)[NotificationViewModel::class.java]
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.notificationRV.adapter = NotificationAdapter(notificationList)
        binding.backIcon.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}