package by.bsuir.vshu.relaxapp.domain.repository


import by.bsuir.vshu.relaxapp.domain.model.Horoscope
import by.bsuir.vshu.relaxapp.domain.model.User
import by.bsuir.vshu.relaxapp.util.Mood
import by.bsuir.vshu.relaxapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface HoroscopeRepository {

    suspend fun getHoroscope(): Horoscope

    fun getRecommendation(mood: Mood): String

    suspend fun getUserById(id: String): User

    suspend fun addUser(user: User): Long

    suspend fun updateUser(user: User)


}