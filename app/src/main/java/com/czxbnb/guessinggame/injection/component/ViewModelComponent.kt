package com.czxbnb.guessinggame.injection.component

import com.czxbnb.guessinggame.injection.module.RepositoryModule
import dagger.Component
import javax.inject.Singleton

/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(RepositoryModule::class)])
interface ViewModelComponent {
    @Component.Builder
    interface Builder {
        fun build() : ViewModelComponent

        fun repositoryModule (repositoryModule: RepositoryModule): Builder
    }

}