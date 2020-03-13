package com.moises.resume.framework.db

import android.content.Context
import androidx.room.Room

class DatabaseBuilder {
    fun buildDatabase(app : Context) : DatabaseConfig = Room.databaseBuilder(app,
        DatabaseConfig::class.java, "moises_resume")
        .fallbackToDestructiveMigration()
        .build()
}