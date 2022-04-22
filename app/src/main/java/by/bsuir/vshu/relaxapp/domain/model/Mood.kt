package by.bsuir.vshu.relaxapp.domain.model

import by.bsuir.vshu.relaxapp.data.local.dao.MoodEntity


data class Mood(

    val id: Int,
    val user_id: String,
    val mood: Int,
    val date: String
)
{
    fun toMoodEntity(): MoodEntity {
        return MoodEntity(
            id = id,
            user_id = user_id,
            mood = mood,
            date = date,
        )
    }
}