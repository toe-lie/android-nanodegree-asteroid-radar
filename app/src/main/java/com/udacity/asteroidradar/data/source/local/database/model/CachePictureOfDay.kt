package com.udacity.asteroidradar.data.source.local.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.udacity.asteroidradar.domain.models.PictureOfDay

@Entity(tableName = "picture_of_day")
data class CachePictureOfDay(
    @field:ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val id: Long,
    @field:ColumnInfo(name = "media_type") val mediaType: String,
    @field:ColumnInfo(name = "title") val title: String,
    @field:ColumnInfo(name = "url") val url: String
) {
  fun toDomain(): PictureOfDay {
    return PictureOfDay(mediaType = mediaType, title = title, url = url)
  }

  companion object {
    fun fromDomain(domainModel: PictureOfDay): CachePictureOfDay {
      return CachePictureOfDay(
          id = 0L,
          mediaType = domainModel.mediaType,
          title = domainModel.title,
          url = domainModel.url)
    }
  }
}
