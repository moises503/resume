package com.moises.resume.core

import android.app.Application
import androidx.room.Room
import com.moises.resume.framework.db.DatabaseBuilder
import com.moises.resume.framework.db.DatabaseConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule(val app : Application) {
    @Provides
    @Singleton
    fun providesAndroidSQLiteDatabase() : DatabaseConfig = DatabaseBuilder().buildDatabase(app)
}