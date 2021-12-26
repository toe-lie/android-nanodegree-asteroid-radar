package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.data.repository.AsteroidRepository
import com.udacity.asteroidradar.ui.model.mappers.UiAsteroidMapper
import com.udacity.asteroidradar.ui.model.UiAsteroid
import com.udacity.asteroidradar.ui.model.UiAsteroidFilter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val asteroidRepository: AsteroidRepository,
    private val uiAsteroidMapper: UiAsteroidMapper
) : ViewModel() {

    private val _asteroids: MutableStateFlow<List<UiAsteroid>> = MutableStateFlow(emptyList())
    val asteroids: StateFlow<List<UiAsteroid>>
        get() = _asteroids

    init {
        loadAsteroids()
    }

    private fun loadAsteroids(filter: UiAsteroidFilter = UiAsteroidFilter.Week) {
        viewModelScope.launch {
            asteroidRepository.getAsteroids(UiAsteroidFilter.to(filter)).collectLatest {
                _asteroids.value = it.map { uiAsteroidMapper.mapToView(it) }
            }
        }
    }

    fun updateFilter(filter: UiAsteroidFilter) {
        loadAsteroids(filter)
    }

}