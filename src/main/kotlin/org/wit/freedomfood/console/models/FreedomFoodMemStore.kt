package org.wit.freedomfood.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class FreedomFoodMemStore : FreedomFoodStore {

    val freedomfoods = ArrayList<FreedomFoodModel>()

    override fun findAll(): List<FreedomFoodModel> {
        return freedomfoods
    }

    override fun findOne(id: Long) : FreedomFoodModel? {
        var foundFreedomFoods: FreedomFoodModel? = freedomfoods.find { p -> p.id == id }
        return foundFreedomFoods
    }

    override fun create(freedomfood: FreedomFoodModel) {
        freedomfood.id = getId()
        freedomfoods.add(freedomfood)
        logAll()
    }

    override fun update(freedomfood: FreedomFoodModel) {
        var foundFreedomFoods = findOne(freedomfood.id!!)
        if (foundFreedomFoods != null) {
            foundFreedomFoods.restaurantname = freedomfood.restaurantname
            foundFreedomFoods.restaurantdescription = freedomfood.restaurantdescription
        }
    }

    internal fun logAll() {
        freedomfoods.forEach { logger.info("${it}") }
    }
}