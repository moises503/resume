package com.moises.resume.framework.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moises.resume.framework.db.entities.ExperienceEntity
import io.reactivex.Single

@Dao
interface ExperiencesDao {
    @Query("SELECT * FROM experiences")
    fun getAllExperiences(): Single<List<ExperienceEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExperience(experienceEntity : ExperienceEntity)

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertAllExperiences(experienceEntities : List<ExperienceEntity>)
}