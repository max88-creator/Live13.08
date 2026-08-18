package com.example.live1308.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUser(item: User): Long
    @Delete
    suspend fun deleteUser(item: User): Int
    @Query("SELECT * FROM user ORDER BY id DESC")
    fun observeUsers(): Flow<List<User>>
    @Query("SELECT * FROM user ORDER BY id ASC")
    suspend fun getAll(): List<User>
}