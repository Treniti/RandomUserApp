package com.treniti.randomuserapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.treniti.randomuserapp.data.db.entity.UserEntity


@Dao
interface HistoryDAO {
    @Insert
    suspend fun saveHistory(userEntities: List<UserEntity>)

    @Query("SELECT * FROM history")
    suspend fun getHistory(): List<UserEntity>
}