package com.clothex.user.log

import com.clothex.data.domain.model.log.EventBase
import com.clothex.user.home.categories.style.DepartmentEnum

object MainLogEvents {
    class OpenProduct(private val productId: String, private val productName: String) :
        EventBase() {
        override val eventName: String = "open_product"
        override fun getParams(): Map<String, String> {
            return mapOf("product_id" to productId, "product_name" to productName)
        }
    }

    class OpenShop(private val shopId: String, private val shopName: String) : EventBase() {
        override val eventName: String = "open_shop"
        override fun getParams(): Map<String, String> {
            return mapOf("shop_id" to shopId, "shop_name" to shopName)
        }
    }

    class AddToMyItems(private val productId: String, private val shopId: String) :
        EventBase() {
        override val eventName: String = "add_my_items"
        override fun getParams(): Map<String, String> {
            return mapOf("product_id" to productId, "shop_id" to shopId)
        }
    }

    class DeleteMyItems(private val myItemsId: String) : EventBase() {
        override val eventName: String = "delete_my_items"
        override fun getParams(): Map<String, String> {
            return mapOf("ids" to myItemsId)
        }
    }

    class OpenOffer(private val offerId: String) : EventBase() {
        override val eventName: String = "open_offer"
        override fun getParams(): Map<String, String> {
            return mapOf("offer_id" to offerId)
        }
    }

    class OpenVoucher(private val voucherId: String) : EventBase() {
        override val eventName: String = "open_voucher"
        override fun getParams(): Map<String, String> {
            return mapOf("voucher_id" to voucherId)
        }
    }

    class OpenOrder(private val orderId: String) : EventBase() {
        override val eventName: String = "open_order"
        override fun getParams(): Map<String, String> {
            return mapOf("order_id" to orderId)
        }
    }

    object AddQRVoucher : EventBase() {
        override val eventName: String = "voucher_qr"
    }

    object AddTextVoucher : EventBase() {
        override val eventName: String = "voucher_text"
    }

    class AddVoucher(private val code: String) : EventBase() {
        override val eventName: String = "add_voucher"
        override fun getParams(): Map<String, String> {
            return mapOf("code" to code)
        }
    }

    class SelectDepartment(private val department: DepartmentEnum) : EventBase() {
        override val eventName: String = "select_department"
        override fun getParams(): Map<String, String> {
            return mapOf("department" to department.value)
        }
    }

    class SelectType(private val typeId: String?, private val typeName: String) : EventBase() {
        override val eventName: String = "select_type"
        override fun getParams(): Map<String, String> {
            return mapOf("type_id" to (typeId ?: ""), "type_name" to typeName)
        }
    }

    class SelectNotification(private val notificationId: String) : EventBase() {
        override val eventName: String = "select_notification"
        override fun getParams(): Map<String, String> {
            return mapOf("notification_id" to notificationId)
        }
    }

}