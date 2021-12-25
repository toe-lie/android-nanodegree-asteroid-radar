package com.udacity.asteroidradar.ui.model.mappers

interface UiMapper<E, V> {
    fun mapToView(domainModel: E): V
}