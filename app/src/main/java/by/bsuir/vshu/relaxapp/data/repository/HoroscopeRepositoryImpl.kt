package by.bsuir.vshu.relaxapp.data.repository



import by.bsuir.vshu.relaxapp.data.local.dao.HoroscopeDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import by.bsuir.vshu.relaxapp.data.remote.HoroscopeApi
import by.bsuir.vshu.relaxapp.data.remote.dto.toHoroscopeEntity
import by.bsuir.vshu.relaxapp.domain.model.Horoscope
import by.bsuir.vshu.relaxapp.domain.repository.HoroscopeRepository
import by.bsuir.vshu.relaxapp.util.Resource

import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class HoroscopeRepositoryImpl @Inject constructor(
    private val api: HoroscopeApi,
    private val dao: HoroscopeDao
) : HoroscopeRepository {

    override fun getHoroscope(): Flow<Resource<Horoscope>> = flow {

        emit(Resource.Loading<Horoscope>())

        val horoscope = dao.getHoroscope().toHoroscope()
        //val horoscope = api.getHoroscope().toHoroscopeEntity().toHoroscope()
        try {
            val remoteItem = api.getHoroscope()
            dao.insertItem(remoteItem.toHoroscopeEntity())
            println("loaded from api")
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = horoscope
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = horoscope
                )
            )
        }

        val newItem = dao.getHoroscope().toHoroscope()
        emit(Resource.Success(newItem))
    }

}
