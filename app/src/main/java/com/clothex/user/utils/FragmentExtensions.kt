package com.clothex.user.utils

import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment

/**
 * Created by Mohamed Elshafey on 22/11/2021.
 */

fun Fragment.hasPermission(permission: String): Boolean {
    return ActivityCompat.checkSelfPermission(requireContext(), permission) ==
            PackageManager.PERMISSION_GRANTED
}