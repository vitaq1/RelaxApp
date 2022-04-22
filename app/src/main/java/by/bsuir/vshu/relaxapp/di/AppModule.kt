package by.bsuir.vshu.relaxapp.di

import android.app.Application
import androidx.room.Room
import by.bsuir.vshu.relaxapp.data.remote.parser.HoroscopeParser
import by.bsuir.vshu.relaxapp.data.local.HoroscopeDatabase
import by.bsuir.vshu.relaxapp.data.remote.HoroscopeApi
import by.bsuir.vshu.relaxapp.data.repository.HoroscopeRepositoryImpl
import by.bsuir.vshu.relaxapp.domain.repository.HoroscopeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHoroscopeParser(): HoroscopeParser {
        return HoroscopeParser()
    }

    @Provides
    @Singleton
    fun provideHoroscopeApi(parser: HoroscopeParser): HoroscopeApi {
        return HoroscopeApi(parser)
    }


    @Provides
    @Singleton
    fun provideHoroscopeRepository(api: HoroscopeApi, db: HoroscopeDatabase): HoroscopeRepository {
        return HoroscopeRepositoryImpl(api, db.dao)
    }


    @Provides
    @Singleton
    fun provideHoroscopeDatabase(app: Application): HoroscopeDatabase {
        return Room.databaseBuilder(
            app, HoroscopeDatabase::class.java, "horoscope_db"
        ).fallbackToDestructiveMigration()
            .build()
    }
}