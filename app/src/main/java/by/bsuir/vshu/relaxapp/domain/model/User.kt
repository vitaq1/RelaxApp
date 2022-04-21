package by.bsuir.vshu.relaxapp.domain.model

import by.bsuir.vshu.relaxapp.data.local.dao.UserEntity


data class User(

    val mail: String,
    val password: String,
    val name: String = "User",
    val weight: Double = 0.0,
    val pressure: Int = 0,
    val image: String = ""


) {
    fun toUserEntity(): UserEntity {
        return UserEntity(
            mail = mail,
            password = password,
            name = name,
            weight = weight,
            pressure = pressure,
            image = image,
        )
    }
}