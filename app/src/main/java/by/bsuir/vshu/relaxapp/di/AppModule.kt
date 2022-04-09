package by.bsuir.vshu.relaxapp.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*@Provides
    @Singleton
    fun provideStoreApi(parser: WebParser): StoreApi {
        return StoreApi(parser)
    }*/


    /*@Provides
    @Singleton
    fun provideItemRepository(api: StoreApi, db: ItemDatabase): ItemRepository {
        return ItemRepositoryImpl(api, db.dao)
    }*/


    /*@Provides
    @Singleton
    fun provideItemDatabase(app: Application): ItemDatabase {
        return Room.databaseBuilder(
            app, ItemDatabase::class.java, "item_db"
        ).fallbackToDestructiveMigration()
            .build()
    }*/
}