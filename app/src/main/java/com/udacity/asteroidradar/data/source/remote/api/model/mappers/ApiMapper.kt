package com.udacity.asteroidradar.data.source.remote.api.model.mappers

interface ApiMapper<E, D> {
    fun mapToDomain(apiEntity: E): D
}
