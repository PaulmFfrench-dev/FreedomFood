package org.wit.freedomfood.console.models

interface FreedomFoodSearchDataStore {
    fun findLatest(): FreedomFoodSearchDataModel?
    fun create(freedomfood: FreedomFoodSearchDataModel)
}