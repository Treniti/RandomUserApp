package com.treniti.randomuserapp.di.module.db

import com.treniti.randomuserapp.data.db.AppDatabase
import com.treniti.randomuserapp.data.db.dao.HistoryDAO
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class HistoryDAOModule {

    @Singleton
    @Provides
    fun provideHistoryDAO(database: AppDatabase): HistoryDAO {
        return database.getHistoryDAO()
    }

}