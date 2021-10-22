package org.wit.freedomfood.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

class FreedomFoodSearchDataMemStore : FreedomFoodSearchDataStore {

    private val freedomfoods = ArrayList<FreedomFoodSearchDataModel>()

    override fun findLatest(): FreedomFoodSearchDataModel {
        return freedomfoods.last()
        logAll()
    }

    override fun create(freedomfood: FreedomFoodSearchDataModel) {
        freedomfoods.add(freedomfood)
        logAll()
    }

    private fun logAll() {
        freedomfoods.forEach { logger.info("$it") }
    }
}