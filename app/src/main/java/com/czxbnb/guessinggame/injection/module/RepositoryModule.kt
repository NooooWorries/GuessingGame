package com.czxbnb.guessinggame.injection.module

import com.czxbnb.guessinggame.models.item.ItemRepository
import dagger.Module
import dagger.Provides
import dagger.Reusable

@Module
object RepositoryModule {
    /**
     * Provides the Item repository implementation.
     * @return the Item repository implementation.
     */
    @Provides
    @Reusable
    @JvmStatic
    internal fun provideItemRepository(): ItemRepository {
        return ItemRepository.getInstance()
    }
}