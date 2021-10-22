package org.wit.freedomfood.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

/**
 * Returns an incrementing ID of type Long
 */
internal fun getId(): Long {
    return lastId++
}

abstract class FreedomFoodMemStore : FreedomFoodStore {

    private val freedomfoods = ArrayList<FreedomFoodModel>()

    /**
     * Returns all list<FreedomFoodModel>
     */
    override fun findAll(): List<FreedomFoodModel> {
        return freedomfoods
    }

    /**
     * Returns a single list<FreedomFoodModel>
     */
    override fun findOne(id: Long): List<FreedomFoodModel> {
        return listOf(freedomfoods.find { p -> p.id == id }!!)
    }

    /**
     * Returns a single FreedomFoodModel
     */
    override fun toEdit(id: Long): FreedomFoodModel? {
        return freedomfoods.find { p -> p.id == id }
    }

    /**
     * Writes a FreedomFoodModel to temporary storage
     */
    override fun create(freedomfood: FreedomFoodModel) {
        freedomfood.id = getId()
        freedomfoods.add(freedomfood)
        logAll()
    }

    /**
     * Writes an updated FreedomFoodModel to temporary storage
     */
    override fun update(freedomfood: FreedomFoodModel) {
        val foundFreedomFoods = toEdit(freedomfood.id)
        if (foundFreedomFoods != null) {
            foundFreedomFoods.restaurantname = freedomfood.restaurantname
            foundFreedomFoods.restaurantdescription = freedomfood.restaurantdescription
        }
    }

    /**
     * Deletes a FreedomFoodModel from temporary storage
     */
    override fun delete(freedomfood: FreedomFoodModel) {
        freedomfoods.remove(freedomfood)
    }

    /**
     * Displays all FreedomFoodModels in the terminal
     */
    private fun logAll() {
        freedomfoods.forEach { logger.info("$it") }
    }
}