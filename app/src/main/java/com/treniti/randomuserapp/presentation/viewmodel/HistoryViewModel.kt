package com.treniti.randomuserapp.presentation.viewmodel

import androidx.lifecycle.*
import com.treniti.randomuserapp.data.db.repository.HistoryRepository
import com.treniti.randomuserapp.data.db.entity.UserEntity
import com.treniti.randomuserapp.data.presentation.UserPresentation
import com.treniti.randomuserapp.data.ResultWrapper
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class HistoryViewModel @Inject constructor(private val repository: HistoryRepository) :
    ViewModel() {

    fun getHistory() = liveData(Dispatchers.IO) {
        emit(ResultWrapper.Loading)
        try {
            val data = repository.getHistory()
            emit(ResultWrapper.Success(convertEntityToPresentation(data)))
        } catch (exception: Exception) {
            emit(ResultWrapper.Error(message = exception.message ?: "Error occurred"))
        }
    }

    private fun convertEntityToPresentation(data: List<UserEntity>): List<UserPresentation> {
        val presentationList: MutableList<UserPresentation> = mutableListOf()
        for (userEntity in data) {
            presentationList.add(UserPresentation.createFromEntity(userEntity))
        }

        return presentationList
    }
}