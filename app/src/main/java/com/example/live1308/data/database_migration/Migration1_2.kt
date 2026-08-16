package com.example.live1308.data.database_migration

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_2 = object: Migration(startVersion = 1, endVersion = 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("ALERT TABLE users ADD COLUMN email TEXT NOT NULL DEFAULT ''")
    }
}