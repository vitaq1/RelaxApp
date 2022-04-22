package by.bsuir.vshu.relaxapp.data.local.dao

import androidx.room.*
import by.bsuir.vshu.relaxapp.domain.model.Photo


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

    @Update
    suspend fun updateUser(user: UserEntity)

    @Query("SELECT * FROM photoentity WHERE user_id = :id")
    suspend fun getPhotosByUserId(id: String): List<PhotoEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPhoto(photo: PhotoEntity)

    @Query("SELECT * FROM photoentity WHERE id = :id")
    suspend fun getPhotoById(id: Int): PhotoEntity

    @Query("DELETE FROM photoentity WHERE id = :id")
    suspend fun deletePhotoById(id: Int)

}