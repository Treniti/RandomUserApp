package com.treniti.randomuserapp.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.treniti.randomuserapp.data.db.entity.UserEntity
import com.treniti.randomuserapp.data.db.repository.HistoryRepository
import com.treniti.randomuserapp.data.network.dto.UserDTO
import com.treniti.randomuserapp.data.network.repository.UserRepository
import com.treniti.randomuserapp.data.presentation.UserPresentation
import com.treniti.randomuserapp.data.ResultWrapper
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class CatalogViewModel @Inject constructor(
    private val networkRepository: UserRepository,
    private val databaseRepository: HistoryRepository
) :
    ViewModel() {

    private val catalogExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun getUsers() = liveData(Dispatchers.IO) {
        emit(ResultWrapper.Loading)
        try {
            val response = networkRepository.getUsers()
            when (response.code()) {
                200 -> {
                    if (response.body() != null) {
                        saveHistory(response.body()!!.results)
                        emit(ResultWrapper.Success(convertDTOToPresentation(response.body()!!.results)))
                    } else {
                        emit(ResultWrapper.Error(message = "Response body is null"))
                    }
                }
                else -> {
                    emit(ResultWrapper.Error(message = "Error occurred. Response code: ${response.code()}"))
                }
            }
        } catch (exception: Exception) {
            emit(ResultWrapper.Error(message = exception.message ?: "Error occurred"))
        }
    }

     private fun saveHistory(data: List<UserDTO>) {
        viewModelScope.launch(Dispatchers.IO + catalogExceptionHandler) {
            databaseRepository.saveHistory(convertDTOToEntity(data))
        }
    }

    private fun convertDTOToPresentation(data: List<UserDTO>): List<UserPresentation> {
        val presentationList: MutableList<UserPresentation> = mutableListOf()
        for (userDTO in data) {
            presentationList.add(UserPresentation.createFromDTO(userDTO))
        }

        return presentationList
    }

    private fun convertDTOToEntity(data: List<UserDTO>): List<UserEntity> {
        val entityList: MutableList<UserEntity> = mutableListOf()
        for (user in data) {
            entityList.add(UserEntity.createFromDTO(user))
        }

        return entityList
    }
}