package by.bsuir.vshu.relaxapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import by.bsuir.vshu.relaxapp.data.local.dao.HoroscopeEntity
import by.bsuir.vshu.relaxapp.data.local.dao.HoroscopeDao


@Database(
    entities = [HoroscopeEntity::class],
    version = 1
)
abstract class HoroscopeDatabase: RoomDatabase() {

        abstract val dao: HoroscopeDao
}