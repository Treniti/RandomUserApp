package com.treniti.randomuserapp.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.treniti.randomuserapp.data.internal.UserDateOfBirthday
import com.treniti.randomuserapp.data.internal.UserPicture
import com.treniti.randomuserapp.data.internal.Username
import com.treniti.randomuserapp.data.network.dto.UserDTO

@Entity(tableName = "history")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val gender: String? = null,
    @Embedded
    val name: Username? = null,
    val city: String? = null,
    val country: String? = null,
    val email: String? = null,
    @Embedded
    val dateOfBirthday: UserDateOfBirthday? = null,
    val phone: String? = null,
    @Embedded
    val picture: UserPicture? = null,
    val dateOfLoading: Long? = null
) {
    companion object {
        fun createFromDTO(userDTO: UserDTO): UserEntity{
            return UserEntity(
                id = 0,
                gender = userDTO.gender,
                name = userDTO.name,
                email = userDTO.email,
                dateOfBirthday = userDTO.dateOfBirthday,
                phone = userDTO.phone,
                picture = userDTO.picture,
                city = userDTO.city,
                country = userDTO.country,
                dateOfLoading = System.currentTimeMillis()
            )
        }
    }
}
