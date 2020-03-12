package com.moises.resume.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moises.resume.framework.db.daos.CoursesDao
import com.moises.resume.framework.db.entities.CourseEntity

@Database(entities = [CourseEntity::class], version = 1)
abstract class DatabaseConfig : RoomDatabase(){
    abstract fun coursesDao() : CoursesDao
}