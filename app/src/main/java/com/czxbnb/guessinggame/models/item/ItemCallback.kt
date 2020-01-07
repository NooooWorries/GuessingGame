package com.czxbnb.guessinggame.models.item

interface ItemCallback {
    fun onLoadItemSuccess(itemList: List<Item>)

    fun onLoadItemError(e: Throwable)
}