package com.example.live1308.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = true
)
abstract class MainDb: RoomDatabase() {
    abstract fun userDao(): Dao
}