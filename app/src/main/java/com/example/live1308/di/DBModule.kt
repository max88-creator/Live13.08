package com.example.live1308.di

import android.app.Application
import androidx.room.Room
import com.example.live1308.data.Dao
import com.example.live1308.data.MainDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DBModule {
    @Provides
    @Singleton
    fun provideMainDB(app: Application): MainDb {
        return Room.databaseBuilder(
            app,
            MainDb::class.java,
            "main_db"
        ).build()
    }
    @Provides
    fun providesUserDao(mainDb: MainDb): Dao {
        return mainDb.userDao()
    }
}