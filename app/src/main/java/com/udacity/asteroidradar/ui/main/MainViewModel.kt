package com.udacity.asteroidradar.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udacity.asteroidradar.data.repository.AsteroidRepository
import com.udacity.asteroidradar.data.repository.PictureOfDayRepository
import com.udacity.asteroidradar.data.source.remote.api.model.mappers.ApiPictureOfDayMapper
import com.udacity.asteroidradar.domain.models.PictureOfDay
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
    private val pictureOfDayRepository: PictureOfDayRepository,
    private val uiAsteroidMapper: UiAsteroidMapper
) : ViewModel() {

    private val _asteroids: MutableStateFlow<List<UiAsteroid>> = MutableStateFlow(emptyList())
    val asteroids: StateFlow<List<UiAsteroid>>
        get() = _asteroids

    private val _pictureOfDay: MutableStateFlow<PictureOfDay?> = MutableStateFlow(null)
    val pictureOfDay: StateFlow<PictureOfDay?>
        get() = _pictureOfDay

    init {
        loadPictureOfDay()
        loadAsteroids()
    }

    private fun loadPictureOfDay() {
        viewModelScope.launch {
            pictureOfDayRepository.getPictureOfDay().collectLatest {
                _pictureOfDay.value = it
            }
        }
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