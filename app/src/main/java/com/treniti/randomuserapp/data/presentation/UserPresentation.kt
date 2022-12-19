package com.treniti.randomuserapp.data.presentation

import android.os.Parcelable
import com.treniti.randomuserapp.data.db.entity.UserEntity
import com.treniti.randomuserapp.data.network.dto.UserDTO
import kotlinx.parcelize.Parcelize


@Parcelize
data class UserPresentation(
    val fullName: String?,
    val gender: String?,
    val photo: String?,
    val age: Int?,
    val email: String?,
    val phone: String?
) : Parcelable {
    companion object {
        fun createFromDTO(userDTO: UserDTO): UserPresentation {
            return UserPresentation(
                fullName = "${userDTO.name?.title} ${userDTO.name?.first} ${userDTO.name?.last}",
                gender = userDTO.gender,
                photo = userDTO.picture?.large,
                age = userDTO.dateOfBirthday?.age,
                email = userDTO.email,
                phone = userDTO.phone
            )
        }

        fun createFromEntity(userEntity: UserEntity): UserPresentation {
            return UserPresentation(
                fullName = "${userEntity.name?.title} ${userEntity.name?.first} ${userEntity.name?.last}",
                gender = userEntity.gender,
                photo = userEntity.picture?.large,
                age = userEntity.dateOfBirthday?.age,
                email = userEntity.email,
                phone = userEntity.phone
            )
        }
    }
}