package by.bsuir.vshu.relaxapp.data.local.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.bsuir.vshu.relaxapp.domain.model.Mood

@Entity
data class MoodEntity(

    @PrimaryKey(autoGenerate = true) val id: Int,
    val user_id: String,
    val mood: Int,
    val date: String
) {
    fun toMood(): Mood {
        return Mood(
            id = id,
            user_id = user_id,
            mood = mood,
            date = date,
        )
    }
}