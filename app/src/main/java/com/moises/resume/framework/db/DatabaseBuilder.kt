package com.moises.resume.framework.db

import android.app.Application
import androidx.room.Room

class DatabaseBuilder {
    fun buildDatabase(app : Application) : DatabaseConfig = Room.databaseBuilder(app,
        DatabaseConfig::class.java, "moises_resume")
        .fallbackToDestructiveMigration()
        .build()
}