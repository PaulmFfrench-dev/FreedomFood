package org.wit.freedomfood.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

abstract class FreedomFoodMemStore : FreedomFoodStore {

    private val freedomfoods = ArrayList<FreedomFoodModel>()

    override fun findAll(): List<FreedomFoodModel> {
        return freedomfoods
    }

    override fun findOne(id: Long): List<FreedomFoodModel> {
        return listOf(freedomfoods.find { p -> p.id == id }!!)
    }

    override fun toEdit(id: Long): FreedomFoodModel? {
        return freedomfoods.find { p -> p.id == id }
    }

    override fun create(freedomfood: FreedomFoodModel) {
        freedomfood.id = getId()
        freedomfoods.add(freedomfood)
        logAll()
    }

    override fun update(freedomfood: FreedomFoodModel) {
        val foundFreedomFoods = toEdit(freedomfood.id)
        if (foundFreedomFoods != null) {
            foundFreedomFoods.restaurantname = freedomfood.restaurantname
            foundFreedomFoods.restaurantdescription = freedomfood.restaurantdescription
        }
    }

    override fun delete(freedomfood: FreedomFoodModel) {
        freedomfoods.remove(freedomfood)
    }

    private fun logAll() {
        freedomfoods.forEach { logger.info("$it") }
    }
}