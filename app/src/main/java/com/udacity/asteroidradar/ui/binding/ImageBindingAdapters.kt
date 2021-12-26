package com.udacity.asteroidradar.ui.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.udacity.asteroidradar.domain.models.Asteroid
import com.udacity.asteroidradar.ui.main.AsteroidListAdapter

@BindingAdapter("imageUrl")
fun loadImage(imageView: ImageView, url: String?) {
  imageView.load(url)
}
