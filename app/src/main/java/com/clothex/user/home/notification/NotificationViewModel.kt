package com.clothex.user.home.notification

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.clothex.data.domain.model.SimpleResponse
import com.clothex.data.domain.model.notification.Notification
import com.clothex.data.domain.usecases.notification.GetNotificationsUseCase
import com.clothex.data.domain.usecases.notification.ReadNotificationsUseCase
import com.clothex.user.base.BaseLanguageViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NotificationViewModel(
    private val getNotificationUseCase: GetNotificationsUseCase,
    private val readNotificationsUseCase: ReadNotificationsUseCase
) : BaseLanguageViewModel() {

    val notificationLiveData =
        MutableLiveData<Result<List<Notification>>>()

    fun fetchNotifications() {
        viewModelScope.launch {
            getNotificationUseCase(Unit).collect {
                notificationLiveData.postValue(it)
            }
        }
    }

    fun readNotification(notificationId: String, callback: (Result<SimpleResponse>) -> Unit) {
        viewModelScope.launch {
            readNotificationsUseCase.invoke(notificationId).collect {
                callback.invoke(it)
            }
        }
    }

}