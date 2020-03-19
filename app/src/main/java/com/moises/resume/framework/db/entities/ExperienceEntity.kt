package com.moises.resume.framework.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "experiences"
)
data class ExperienceEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "company")
    val company: String,
    @ColumnInfo(name = "position")
    val position: String
)
