package com.udacity.asteroidradar.domain.models

data class PictureOfDay(val mediaType: String, val title: String, val url: String) {
  val imageUrl: String =
      if (mediaType == "image") {
        url
      } else {
        ""
      }
}
