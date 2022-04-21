package by.bsuir.vshu.relaxapp.data.local.dao


import androidx.room.Entity
import androidx.room.PrimaryKey
import by.bsuir.vshu.relaxapp.domain.model.User

@Entity
data class UserEntity(

    @PrimaryKey val mail: String,
    val password: String,
    val name: String = "User",
    val weight: Double = 0.0,
    val pressure: Int = 0,
    val image: String = ""
) {
    fun toUser(): User {
        return User(
            mail = mail,
            password = password,
            name = name,
            weight = weight,
            pressure = pressure,
            image = image,
        )
    }
}