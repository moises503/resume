package com.moises.resume.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.moises.resume.framework.db.daos.CoursesDao
import com.moises.resume.framework.db.daos.ExperiencesDao
import com.moises.resume.framework.db.daos.ProfilesDao
import com.moises.resume.framework.db.entities.CourseEntity
import com.moises.resume.framework.db.entities.ExperienceEntity
import com.moises.resume.framework.db.entities.ProfileEntity

@Database(entities = [CourseEntity::class, ProfileEntity::class, ExperienceEntity::class], version = 1)
abstract class DatabaseConfig : RoomDatabase(){
    abstract fun coursesDao() : CoursesDao
    abstract fun profilesDao() : ProfilesDao
    abstract fun experiencesDao() : ExperiencesDao
}