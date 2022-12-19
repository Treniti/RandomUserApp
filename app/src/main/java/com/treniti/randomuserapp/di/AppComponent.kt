package com.treniti.randomuserapp.di

import android.content.Context
import com.treniti.randomuserapp.di.module.AppModule
import com.treniti.randomuserapp.presentation.fragment.CatalogFragment
import com.treniti.randomuserapp.presentation.fragment.HistoryFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(catalogFragment: CatalogFragment)
    fun inject(catalogFragment: HistoryFragment)

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}