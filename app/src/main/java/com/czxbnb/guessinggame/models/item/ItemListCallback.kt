package com.czxbnb.guessinggame.models.item

interface ItemListCallback {
    fun onLoadItemListSuccess(itemList: List<Item>)

    fun onLoadItemError(e: Throwable)
}