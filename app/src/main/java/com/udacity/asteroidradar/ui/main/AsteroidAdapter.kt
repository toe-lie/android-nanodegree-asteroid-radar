package com.udacity.asteroidradar.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.databinding.AsteroidListItemBinding
import com.udacity.asteroidradar.ui.model.UiAsteroid

class AsteroidListAdapter constructor(private val onClickListener: OnClickListener) :
    ListAdapter<UiAsteroid, AsteroidListAdapter.AsteroidViewHolder>(AsteroidDiffCallback) {

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsteroidViewHolder {
    val binding =
        AsteroidListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    return AsteroidViewHolder(binding, onClickListener)
  }

  override fun onBindViewHolder(holder: AsteroidViewHolder, position: Int) {
    val item = getItem(position)
    holder.bind(item)
  }

  class AsteroidViewHolder(private var binding: AsteroidListItemBinding, private val onClickListener: OnClickListener) :
      RecyclerView.ViewHolder(binding.root) {

    fun bind(item: UiAsteroid) {
      binding.item = item
      binding.onClickListener = onClickListener
    }
  }

  object AsteroidDiffCallback : DiffUtil.ItemCallback<UiAsteroid>() {
    override fun areItemsTheSame(oldItem: UiAsteroid, newItem: UiAsteroid): Boolean {
      return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: UiAsteroid, newItem: UiAsteroid): Boolean {
      return oldItem == newItem
    }
  }

  class OnClickListener(val clickListener: (item: UiAsteroid) -> Unit) {
    fun onClick(item: UiAsteroid) = clickListener(item)
  }
}
