package com.moises.resume.framework.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moises.resume.framework.db.entities.CourseEntity
import io.reactivex.Single


@Dao
interface CoursesDao {
    @Query("SELECT * FROM courses")
    fun getAllCourses(): Single<List<CourseEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourse(courseEntity : CourseEntity)

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertAllCourses(courseEntities : List<CourseEntity>)
}