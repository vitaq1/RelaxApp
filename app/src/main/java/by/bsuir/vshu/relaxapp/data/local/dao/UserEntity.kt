package by.bsuir.vshu.relaxapp.data.local.dao


import androidx.room.Entity
import androidx.room.PrimaryKey
import by.bsuir.vshu.relaxapp.domain.model.User

@Entity
data class UserEntity(

    @PrimaryKey val mail: String,
    val password: String,
    var name: String = "User",
    var age: Int = 0,
    var weight: Double = 0.0,
    var pressure: Int = 0,
    var image: String = ""
) {
    fun toUser(): User {
        return User(
            mail = mail,
            password = password,
            name = name,
            age = age,
            weight = weight,
            pressure = pressure,
            image = image,
        )
    }
}