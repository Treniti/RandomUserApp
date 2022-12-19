package com.treniti.randomuserapp.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.treniti.randomuserapp.presentation.viewmodel.CatalogViewModel
import com.treniti.randomuserapp.presentation.viewmodel.HistoryViewModel
import com.treniti.randomuserapp.presentation.viewmodel.base.ViewModelFactory
import com.treniti.randomuserapp.presentation.viewmodel.base.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CatalogViewModel::class)
    internal abstract fun catalogViewModel(viewModel: CatalogViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HistoryViewModel::class)
    internal abstract fun historyViewModel(viewModel: HistoryViewModel): ViewModel
}