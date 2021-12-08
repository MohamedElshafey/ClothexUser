package com.clothex.user.data

import android.content.Context
import com.clothex.shop.core.models.Item
import com.clothex.user.R
import com.clothex.user.data.my_items.MinimalProduct
import com.clothex.user.data.my_items.MyItem
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.*


/**
 * Created by Mohamed Elshafey on 29/11/2021.
 */


fun getItemsList(context: Context): List<Item> {
    val inputStream: InputStream = context.resources.openRawResource(R.raw.items)
    val writer: Writer = StringWriter()
    val buffer = CharArray(1024)
    inputStream.use {
        val reader: Reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
        var n: Int
        while (reader.read(buffer).also { n = it } != -1) {
            writer.write(buffer, 0, n)
        }
    }

    val jsonString: String = writer.toString()
    return Gson().fromJson(jsonString, object : TypeToken<List<Item?>?>() {}.type)
}

val notificationList = listOf(
    Notification(
        "Welcome to our new App!",
        "Our app here to help you get your items!",
        "https://i.pinimg.com/originals/d7/af/32/d7af326b85e62c293dba7bad9f4f1757.jpg",
        false
    ), Notification(
        "Welcome to our new App!",
        "Our app here to help you get your items!",
        "https://i.pinimg.com/originals/d7/af/32/d7af326b85e62c293dba7bad9f4f1757.jpg",
        false
    ), Notification(
        "Welcome to our new App!",
        "Our app here to help you get your items!",
        "https://i.pinimg.com/originals/d7/af/32/d7af326b85e62c293dba7bad9f4f1757.jpg",
        true
    ), Notification(
        "Welcome to our new App!",
        "Our app here to help you get your items!",
        "https://i.pinimg.com/originals/d7/af/32/d7af326b85e62c293dba7bad9f4f1757.jpg",
        true
    ), Notification(
        "Welcome to our new App!",
        "Our app here to help you get your items!",
        "https://i.pinimg.com/originals/d7/af/32/d7af326b85e62c293dba7bad9f4f1757.jpg",
        false
    ), Notification(
        "Welcome to our new App!",
        "Our app here to help you get your items!",
        "https://i.pinimg.com/originals/d7/af/32/d7af326b85e62c293dba7bad9f4f1757.jpg",
        false
    ), Notification(
        "Welcome to our new App!",
        "Our app here to help you get your items!",
        "https://i.pinimg.com/originals/d7/af/32/d7af326b85e62c293dba7bad9f4f1757.jpg",
        false
    ), Notification(
        "Welcome to our new App!",
        "Our app here to help you get your items!",
        "https://i.pinimg.com/originals/d7/af/32/d7af326b85e62c293dba7bad9f4f1757.jpg",
        true
    ), Notification(
        "Welcome to our new App!",
        "Our app here to help you get your items!",
        "https://i.pinimg.com/originals/d7/af/32/d7af326b85e62c293dba7bad9f4f1757.jpg",
        false
    )
)

val shopList = listOf(
    Shop(
        logoUrl = "https://i.pinimg.com/originals/d7/af/32/d7af326b85e62c293dba7bad9f4f1757.jpg",
        name = "H&M",
        addressName = "20,Abbas El Akkad - Nasr City",
        workingHour = "Mon to Sat - 9:00 AM . 11:00 PM"
    ), Shop(
        logoUrl = "https://www.rinnoo.net/f/res/s07/locations-photos/000/820/0082067-269-rinnoo-d25fe1d753cf44a39cbb353f8bf17581.jpg",
        name = "ZARA",
        addressName = "20, Makram Ebeid - Nasr City",
        workingHour = "Mon to Sat - 9:00 AM . 11:00 PM"
    ), Shop(
        logoUrl = "https://cdn.freelogovectors.net/wp-content/uploads/2019/11/lc-waikiki-logo.png",
        name = "LC WAIKIKI",
        addressName = "20,Abbas El Akkad - Nasr City",
        workingHour = "Mon to Sat - 9:00 AM . 11:00 PM"
    ), Shop(
        logoUrl = "https://cdn.freelogovectors.net/wp-content/uploads/2019/11/lc-waikiki-logo.png",
        name = "LC WAIKIKI",
        addressName = "20,Abbas El Akkad - Nasr City",
        workingHour = "Mon to Sat - 9:00 AM . 11:00 PM"
    )
)

val savedLocations = listOf(
    SavedLocation(
        title = "New Cairo",
        subTitle = "5th settlement, Villa 202, North 90",
        isSelected = true
    ), SavedLocation(
        title = "Nasr city",
        subTitle = "Markam ebeid",
        isSelected = false
    ), SavedLocation(
        title = "Nasr city",
        subTitle = "Abbas El Akkad",
        isSelected = false
    )
)

val minimalProductList = listOf(
    MinimalProduct(
        title = "Top or Upper body Shirt Long sleeve jersey",
        colorCode = "#FDD9B5",
        sizeName = "2XL",
        quantity = 1,
        image = Media(
            _id = "618c3c9df3e27e0496197d2b",
            source = "http:/localhost:3000/images/cb1834ba32ff453305d0e7877746d3e6.jpg",
            thumbnail = "http:/localhost:3000/images/thumb/cb1834ba32ff453305d0e7877746d3e6.jpg",
            type = "photo"
        ),
        price = 235
    ), MinimalProduct(
        title = "Top or Upper body Shirt Long sleeve jersey",
        colorCode = "#F75394",
        sizeName = "40",
        quantity = 3,
        image = Media(
            _id = "618c3c9df3e27e0496197d2b",
            source = "http:/localhost:3000/images/a905f38e8fdd43c1ca039cb3f8a850ae.jpg",
            thumbnail = "http:/localhost:3000/images/thumb/a905f38e8fdd43c1ca039cb3f8a850ae.jpg",
            type = "photo"
        ),
        price = 500
    ), MinimalProduct(
        title = "Dress",
        colorCode = "#1A4876",
        sizeName = "2XL",
        quantity = 1,
        image = Media(
            _id = "618c3c9df3e27e0496197d2b",
            source = "http:/localhost:3000/images/d405c7a7145587edefa216c70794d679.jpg",
            thumbnail = "http:/localhost:3000/images/thumb/cb1834ba32ff453305d0e7877746d3e6.jpg",
            type = "photo"
        ),
        price = 123
    ), MinimalProduct(
        title = "Top or Upper body Shirt Long sleeve jersey",
        colorCode = "#FDD9B5",
        sizeName = "2XL",
        quantity = 1,
        image = Media(
            _id = "618c3c9df3e27e0496197d2b",
            source = "http:/localhost:3000/images/cb1834ba32ff453305d0e7877746d3e6.jpg",
            thumbnail = "http:/localhost:3000/images/thumb/cb1834ba32ff453305d0e7877746d3e6.jpg",
            type = "photo"
        ),
        price = 324
    ), MinimalProduct(
        title = "Top or Upper body Shirt Long sleeve jersey",
        colorCode = "#F75394",
        sizeName = "2XL",
        quantity = 1,
        image = Media(
            _id = "618c3c9df3e27e0496197d2b",
            source = "http:/localhost:3000/images/cb1834ba32ff453305d0e7877746d3e6.jpg",
            thumbnail = "http:/localhost:3000/images/thumb/cb1834ba32ff453305d0e7877746d3e6.jpg",
            type = "photo"
        ),
        price = 152
    ), MinimalProduct(
        title = "Top or Upper body Shirt Long sleeve jersey",
        colorCode = "#FFffff",
        sizeName = "2XL",
        quantity = 1,
        image = Media(
            _id = "618c3c9df3e27e0496197d2b",
            source = "http:/localhost:3000/images/cb1834ba32ff453305d0e7877746d3e6.jpg",
            thumbnail = "http:/localhost:3000/images/thumb/cb1834ba32ff453305d0e7877746d3e6.jpg",
            type = "photo"
        ),
        price = 623
    )
)

val myItems = listOf(
    MyItem(
        Shop(
            logoUrl = "https://cdn.freelogovectors.net/wp-content/uploads/2019/11/lc-waikiki-logo.png",
            name = "LC WAIKIKI",
            addressName = "20,Abbas El Akkad - Nasr City",
            workingHour = "Mon to Sat - 9:00 AM . 11:00 PM"
        ),
        minimalProductList.shuffled()
    ), MyItem(
        Shop(
            logoUrl = "https://i.pinimg.com/originals/d7/af/32/d7af326b85e62c293dba7bad9f4f1757.jpg",
            name = "LC WAIKIKI",
            addressName = "20,Abbas El Akkad - Nasr City",
            workingHour = "Mon to Sat - 9:00 AM . 11:00 PM"
        ),
        minimalProductList.plus(minimalProductList).shuffled()
    )
)