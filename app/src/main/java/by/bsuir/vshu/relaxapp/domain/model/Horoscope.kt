package by.bsuir.vshu.relaxapp.domain.model

import by.bsuir.vshu.relaxapp.data.local.dao.HoroscopeEntity

data class Horoscope(
    val id: Int,
    val date: String,
    val description: String
) {
    fun toHoroscopeEntity(): HoroscopeEntity {
        return HoroscopeEntity(
            id = id,
            date = date,
            description = description
        )
    }
}