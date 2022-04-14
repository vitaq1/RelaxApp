package by.bsuir.vshu.relaxapp.data.local.dao

import androidx.room.*

import by.bsuir.vshu.relaxapp.data.HoroscopeEntity


@Dao
interface HoroscopeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(items: HoroscopeEntity)

    @Query("SELECT * FROM horoscopeentity")
    suspend fun getHoroscope(): HoroscopeEntity
}