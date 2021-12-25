package com.udacity.asteroidradar.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "picture_of_day")
data class CachePictureOfDay(
    @field:ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long,
    @field:ColumnInfo(name = "media_type") val mediaType: String,
    @field:ColumnInfo(name = "title") val title: String,
    @field:ColumnInfo(name = "url") val url: String
)
