package com.treniti.randomuserapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.treniti.randomuserapp.data.db.dao.HistoryDAO
import com.treniti.randomuserapp.data.db.entity.UserEntity
import com.treniti.randomuserapp.constants.Constants

@Database(entities = [UserEntity::class], version = 1)
abstract class AppDatabase() : RoomDatabase() {
    abstract fun getHistoryDAO(): HistoryDAO

    companion object {

        private var databaseInstance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (databaseInstance == null) {
                databaseInstance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    Constants.DATABASE_NAME
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return databaseInstance!!
        }
    }
}