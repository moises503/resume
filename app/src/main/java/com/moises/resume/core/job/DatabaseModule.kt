package com.moises.resume.core.job

import android.app.Application
import androidx.room.Room
import com.moises.resume.framework.db.DatabaseConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(val app : Application) {
    @Provides
    @Singleton
    fun providesAndroidSQLiteDatabase() : DatabaseConfig = Room.databaseBuilder(app,
        DatabaseConfig::class.java, "moises_resume")
        .fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()
}