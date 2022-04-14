package by.bsuir.vshu.relaxapp.domain.repository


import by.bsuir.vshu.relaxapp.domain.model.Horoscope
import by.bsuir.vshu.relaxapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface HoroscopeRepository {

    fun getHoroscope(): Flow<Resource<Horoscope>>

/*    suspend fun updateItem(item: Item)

    fun getResults(): Flow<Resource<List<Result>>>

    suspend fun insertResult(result: Result)*/

}