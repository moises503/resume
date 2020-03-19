package com.moises.resume.framework.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.moises.resume.framework.db.entities.ProfileEntity
import io.reactivex.Single

@Dao
interface ProfilesDao {
    @Query("SELECT * FROM profiles")
    fun getAllProfiles(): Single<List<ProfileEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertProfile(profileEntity : ProfileEntity)

    @Insert(
        onConflict = OnConflictStrategy.REPLACE
    )
    fun insertAllProfiles(profileEntities : List<ProfileEntity>)
}