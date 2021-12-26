package com.udacity.asteroidradar.ui.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.ui.main.AsteroidListAdapter
import com.udacity.asteroidradar.ui.model.UiAsteroid

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<UiAsteroid>?) {
  val adapter = recyclerView.adapter as AsteroidListAdapter
  adapter.submitList(data)
}
