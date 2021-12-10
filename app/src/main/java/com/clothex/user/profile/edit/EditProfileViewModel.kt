package com.clothex.user.profile.edit

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel

/**
 * Created by Mohamed Elshafey on 10/12/2021.
 */
class EditProfileViewModel : ViewModel() {
    val userName = ObservableField("Mohamed Elshafey")
    val phoneNumber = ObservableField("01116759288")
    val email = ObservableField("mo.a.elshafey@gmail.com")
}