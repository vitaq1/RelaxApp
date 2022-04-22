package by.bsuir.vshu.relaxapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import by.bsuir.vshu.relaxapp.data.local.dao.*


@Database(
    entities = [HoroscopeEntity::class, UserEntity::class, MoodEntity::class, PhotoEntity::class],
    version = 10
)
abstract class HoroscopeDatabase: RoomDatabase() {

        abstract val dao: HoroscopeDao
}