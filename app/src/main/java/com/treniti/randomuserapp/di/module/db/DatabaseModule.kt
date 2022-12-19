package com.treniti.randomuserapp.di.module.db

import android.content.Context
import com.treniti.randomuserapp.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [HistoryDAOModule::class])
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase {
       return AppDatabase.getInstance(context)
    }
}