package by.bsuir.vshu.relaxapp.data.remote

import by.bsuir.vshu.relaxapp.data.remote.parser.HoroscopeParser
import by.bsuir.vshu.relaxapp.data.remote.dto.HoroscopeDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class HoroscopeApi @Inject constructor(private val parser: HoroscopeParser) {

    suspend fun getHoroscope(): HoroscopeDto {
        val horoscope: HoroscopeDto
        withContext(Dispatchers.Default) {
            horoscope = parser.getItem()
        }
        return horoscope
    }

}