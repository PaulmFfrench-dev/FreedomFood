package org.wit.freedomfood.console.models

interface FreedomFoodStore {
    fun findAll(): List<FreedomFoodModel>
    fun findOne(id: Long): FreedomFoodModel?
    fun create(freedomfood: FreedomFoodModel)
    fun update(freedomfood: FreedomFoodModel)
    fun delete(placemark: FreedomFoodModel)
}