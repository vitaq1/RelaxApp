package by.bsuir.vshu.relaxapp.data.local.dao

import androidx.room.*


@Dao
interface HoroscopeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(items: HoroscopeEntity)

    @Query("SELECT * FROM horoscopeentity")
    suspend fun getHoroscope(): HoroscopeEntity

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEntity): Long

    @Query("SELECT * FROM userentity WHERE mail = :id")
    suspend fun getUserById(id: String): UserEntity
}