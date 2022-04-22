package by.bsuir.vshu.relaxapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import by.bsuir.vshu.relaxapp.data.local.dao.HoroscopeEntity
import by.bsuir.vshu.relaxapp.data.local.dao.HoroscopeDao
import by.bsuir.vshu.relaxapp.data.local.dao.MoodEntity
import by.bsuir.vshu.relaxapp.data.local.dao.UserEntity


@Database(
    entities = [HoroscopeEntity::class, UserEntity::class, MoodEntity::class],
    version = 5
)
abstract class HoroscopeDatabase: RoomDatabase() {

        abstract val dao: HoroscopeDao
}