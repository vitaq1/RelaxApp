package by.bsuir.vshu.relaxapp.data.repository


import by.bsuir.vshu.relaxapp.data.local.dao.HoroscopeDao
import by.bsuir.vshu.relaxapp.data.local.dao.UserEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

import by.bsuir.vshu.relaxapp.data.remote.HoroscopeApi
import by.bsuir.vshu.relaxapp.data.remote.dto.toHoroscopeEntity
import by.bsuir.vshu.relaxapp.domain.model.Horoscope
import by.bsuir.vshu.relaxapp.domain.model.User
import by.bsuir.vshu.relaxapp.domain.repository.HoroscopeRepository
import by.bsuir.vshu.relaxapp.util.Mood
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


        val horoscopeEntity = dao.getHoroscope()
        var horoscope: Horoscope? = null
        if (horoscopeEntity!= null) horoscope = horoscopeEntity.toHoroscope()
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

    override fun getRecommendation(mood: Mood) =
        when (mood) {
            Mood.CALM -> "Как сохранить спокойствие? Тренируйте медленное и глубокое дыхание тогда, когда вы чувствуете себя комфортно и расслабленно, чтобы вы могли автоматически прибегнуть к нему в стрессовой или любой другой ситуации, когда вы нервничаете. Вдохните воздух, удерживайте его в течение 5 секунд и затем медленно выдохните. Обратите внимание на то, как в вашем теле при этом снижается напряжение."
            Mood.RELAX -> "В сложные и напряжённые моменты очень важно «отдалиться» от ситуации не только умственно, но и физически. Найдите спокойное место, где вам удобно и комфортно, и подумайте о чём угодно кроме того, что причиняет вам беспокойство.\nЕсли у вас есть возможность отправиться на природу — сделайте это! Лес, горы, пляж — всё, что вам доступно. Природа поможет вам расслабиться."
            Mood.FOCUS -> "Иногда мы сталкиваемся с ситуациями, в которых по различным причинам мы испытываем чувство вины и очень переживаем из-за этого.\nВ таких случаях для сохранения спокойствия важно осознать совершённые ошибки, постараться простить себя, чтобы избавиться от этого чувства вины. Если мы уверены в том, что совершили ошибку, нужно уметь простить себя и дать себе ещё один шанс.\nПостарайтесь увидеть положительную сторону в происходящем и отнестись к ситуации с чувством юмора. Посмейтесь над собой, над обстоятельствами, даже если покажется вам сложным!"
            Mood.EXCITED -> "Перестаньте думать о плохом и мыслить негативно, и забудьте о таких мыслях, как «это невозможно, я не смогу справиться с таким количеством работы», «я ни на что не способен», «я не смогу вынести эту ситуацию» и т.д. — подобными умозаключениями вы только увеличите свой стресс и всё усложните. Постарайтесь увидеть положительные моменты."
            Mood.FUN -> "Радость — это эмоция. Многие думают, что ими управлять невозможно, но это не так. Эмоции вызываются мыслями. Если сейчас вы испытываете грусть, спросите себя, какая ваша мысль вызывает грусть. Проанализируйте, если ли в вашем ответе серьезные основания испытывать негативные эмоции. В большинстве случаев, сразу после этого приходит умиротворение, а затем радость."
        }

    override suspend fun getUserById(id: String): User {
        val user: UserEntity = dao.getUserById(id)
        return if (user != null)
            dao.getUserById(id).toUser()
        else user
    }

    override suspend fun addUser(user: User): Long {
        return dao.insertUser(user.toUserEntity())
    }

}
