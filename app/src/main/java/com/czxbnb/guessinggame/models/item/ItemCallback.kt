package com.czxbnb.guessinggame.models.item

import com.czxbnb.guessinggame.base.BaseData

interface ItemCallback {
    fun onLoadItemSuccess(itemList: List<Item>)

    fun onLoadItemError(e: Throwable)
}