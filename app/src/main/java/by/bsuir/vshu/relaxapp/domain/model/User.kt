package by.bsuir.vshu.relaxapp.domain.model

import by.bsuir.vshu.relaxapp.data.local.dao.UserEntity


data class User(

    val mail: String,
    val password: String,
    var name: String = "User",
    var age: Int = 0,
    var weight: Double = 0.0,
    var height: Int = 0,
    var pressure: Int = 0,
    var image: String = ""


) {
    fun toUserEntity(): UserEntity {
        return UserEntity(
            mail = mail,
            password = password,
            name = name,
            age = age,
            weight = weight,
            height = height,
            pressure = pressure,
            image = image,
        )
    }
}