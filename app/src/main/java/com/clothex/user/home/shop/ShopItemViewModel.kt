package com.clothex.user.home.shop

import com.clothex.data.domain.model.home.HomeShop
import com.clothex.user.base.BaseLanguageViewModel

/**
 * Created by Mohamed Elshafey on 20/11/2021.
 */
class ShopItemViewModel(private val shop: HomeShop) : BaseLanguageViewModel() {

    val logoUrl = shop.logo?.source

    val name = shop.getName(isArabic())

//    val address = shop.address.name

//    val workingHour = getWorkingHourTitle()

//    private fun getWorkingHourTitle(): String {
//        val sdf = SimpleDateFormat("EEEE", Locale.ENGLISH)
//        val d = Date()
//        val dayOfTheWeek: String = sdf.format(d)
//        val todayWorkingHours = shop.workingHours?.findLast {
//            it.title.equals(dayOfTheWeek, true)
//        }
//        todayWorkingHours?.let {
//            return "${it.title} ${it.from} ${it.to}"
//        }
//        return ""
//    }
}