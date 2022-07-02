package com.clothex.data.domain.model.log

abstract class EventBase {
    abstract val eventName: String
    open fun getParams(): Map<String, String>? = null
}