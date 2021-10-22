package org.wit.freedomfood.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class FreedomFoodSearchDataMemStore : FreedomFoodSearchDataStore {

    private val freedomfoods = ArrayList<FreedomFoodSearchDataModel>()

    /**
     * Returns the last record of FreedomFoodSearchDataModel
     */
    override fun findLatest(): FreedomFoodSearchDataModel {
        return freedomfoods.last()
    }

    /**
     * Adds a record to the FreedomFoodSearchDataModel temporary storage
     */
    override fun create(freedomfood: FreedomFoodSearchDataModel) {
        freedomfoods.add(freedomfood)
        logAll()
    }

    /**
     * Displays all FreedomFoodSearchDataModels in the terminal
     */
    private fun logAll() {
        freedomfoods.forEach { logger.info("$it") }
    }
}