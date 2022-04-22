package by.bsuir.vshu.relaxapp.data.local.dao

import androidx.room.Entity
import androidx.room.PrimaryKey
import by.bsuir.vshu.relaxapp.domain.model.Photo

@Entity
data class PhotoEntity(

    @PrimaryKey(autoGenerate = true) val id: Int,
    val user_id: String,
    val uri: String,
    val date: String
) {
    fun toPhoto(): Photo {
        return Photo(
            id = id,
            user_id = user_id,
            uri = uri,
            date = date,
        )
    }
}