package com.czxbnb.guessinggame.injection.component

import com.czxbnb.guessinggame.injection.module.ApiModule
import com.czxbnb.guessinggame.models.item.ItemRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApiModule::class)])
interface RepositoryComponent {
    @Component.Builder
    interface Builder {
        fun build() : RepositoryComponent

        fun apiModule(apiModule: ApiModule): Builder
    }

    /**
     * Injects required dependencies into the specified itemRepository
     * @param itemRepository itemRepository in which to inject the dependencies
     */
    fun inject(itemRepository: ItemRepository)
}