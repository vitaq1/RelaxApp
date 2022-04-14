package by.bsuir.vshu.relaxapp.data.remote.dto

import by.bsuir.vshu.relaxapp.data.HoroscopeEntity

data class HoroscopeDto(
    val id: Int,
    val date: String,
    val description: String,
)

fun HoroscopeDto.toHoroscopeEntity(): HoroscopeEntity {
    return HoroscopeEntity(
        id = id,
        date = date,
        description = description
    )
}