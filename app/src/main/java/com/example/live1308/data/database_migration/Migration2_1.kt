package com.example.live1308.data.database_migration

import androidx.room.migration.Migration
import androidx.sqlite.SQLiteConnection
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_2_3 = object: Migration(startVersion = 2, endVersion = 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALERT TABLE users COLUMN age INTEGER NOT NULL DEFAULT 0")
    }
}