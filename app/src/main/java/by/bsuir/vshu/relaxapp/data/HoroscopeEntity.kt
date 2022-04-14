package by.bsuir.vshu.relaxapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.bsuir.vshu.relaxapp.domain.model.Horoscope

@Entity
data class HoroscopeEntity(
    @PrimaryKey val id: Int,
    val date: String,
    val description: String,
) {
    fun toHoroscope(): Horoscope {
        return Horoscope(
            id = id,
            date = date,
            description = description
        )
    }
}