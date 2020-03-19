package com.moises.resume.framework.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "profiles"
)
data class ProfileEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id : Int,
    @ColumnInfo(name = "photo")
    val photo: String?,
    @ColumnInfo(name = "fullName")
    val fullName: String?,
    @ColumnInfo(name = "contactInfo")
    val contactInfo: String?,
    @ColumnInfo(name = "birthDate")
    val birthDate: String?,
    @ColumnInfo(name = "libs")
    val libs: String,
    @ColumnInfo(name = "archs")
    val archs: String,
    @ColumnInfo(name = "interests")
    val interests: String
)