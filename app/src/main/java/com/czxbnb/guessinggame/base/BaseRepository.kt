package com.czxbnb.guessinggame.base

import com.czxbnb.guessinggame.injection.component.DaggerRepositoryComponent
import com.czxbnb.guessinggame.injection.component.RepositoryComponent
import com.czxbnb.guessinggame.injection.module.ApiModule
import com.czxbnb.guessinggame.models.item.ItemRepository

abstract class BaseRepository {
    private val component: RepositoryComponent = DaggerRepositoryComponent
        .builder()
        .apiModule(ApiModule)
        .build()

    init {
        inject()
    }

    private fun inject() {
        when (this) {
            is ItemRepository -> component.inject(this)
        }
    }
}