package com.treniti.randomuserapp.data.db.repository

import com.treniti.randomuserapp.data.db.dao.HistoryDAO
import com.treniti.randomuserapp.data.db.entity.UserEntity
import javax.inject.Inject

class HistoryRepository @Inject constructor(private val dao: HistoryDAO) {
    suspend fun getHistory() = dao.getHistory()
    suspend fun saveHistory(data: List<UserEntity>) = dao.saveHistory(data)
}