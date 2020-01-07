package com.czxbnb.guessinggame.base

data class BaseData<T>(
    val product: String,
    val resultSize: Int,
    val version: Int,
    val items: T?
)