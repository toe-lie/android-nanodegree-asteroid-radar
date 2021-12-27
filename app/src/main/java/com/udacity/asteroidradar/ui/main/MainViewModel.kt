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
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private val DEFAULT_FILTER = UiAsteroidFilter.Week

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

    private var _currentFilter = DEFAULT_FILTER

    private val _uiState: MutableStateFlow<AsteroidUiState> = MutableStateFlow(AsteroidUiState())
    val uiState: StateFlow<AsteroidUiState>
        get() = _uiState

    init {
        loadPictureOfDay()
        loadAsteroids()
    }

    private fun loadPictureOfDay() {
        viewModelScope.launch {
            pictureOfDayRepository.getPictureOfDay().collectLatest { pictureOfDay ->
                _pictureOfDay.value = pictureOfDay
                _uiState.update {
                    it.copy(pictureOfDay = pictureOfDay)
                }
            }
        }
    }

    private fun loadAsteroids(filter: UiAsteroidFilter = DEFAULT_FILTER) {
        viewModelScope.launch {
            asteroidRepository.getAsteroids(UiAsteroidFilter.to(filter)).collectLatest { asteroids ->
                _asteroids.value = asteroids.map { uiAsteroidMapper.mapToView(it) }
                _uiState.update {
                    it.copy(asteroidItems = asteroids.map { uiAsteroidMapper.mapToView(it) })
                }
            }
        }
    }

    fun updateFilter(filter: UiAsteroidFilter) {
        if (filter == _currentFilter) return

        _currentFilter = filter
        loadAsteroids(filter)
    }

}