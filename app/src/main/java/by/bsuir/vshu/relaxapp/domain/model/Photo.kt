package by.bsuir.vshu.relaxapp.domain.model

import by.bsuir.vshu.relaxapp.data.local.dao.PhotoEntity
import by.bsuir.vshu.relaxapp.data.local.dao.UserEntity

data class Photo(

    val id: Int,
    val user_id: String,
    val uri: String,
    val date: String
) {
    fun toPhotoEntity(): PhotoEntity {
        return PhotoEntity(
            id = id,
            user_id = user_id,
            uri = uri,
            date = date,
        )
    }
}