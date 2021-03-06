package com.czxbnb.guessinggame.models.item

interface ItemCallback {
    fun onLoadItemSuccess(item: Item)

    fun onLoadItemError(e: Throwable)
}