package org.wit.freedomfood.console.controllers

import mu.KotlinLogging
import org.wit.freedomfood.console.models.FreedomFoodJSONStore
import org.wit.freedomfood.console.models.FreedomFoodModel
import org.wit.freedomfood.console.views.FreedomFoodView

class FreedomFoodController {

    private val freedomfoods = FreedomFoodJSONStore()
    private val freedomfoodView = FreedomFoodView()
    private val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching FreedomFood Console App" }
        println("FreedomFood Kotlin App Version 5.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> delete()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down FreedomFood Console App" }
    }

    private fun menu() :Int { return freedomfoodView.menu() }

    private fun add(){
        val afreedomfood = FreedomFoodModel()

        if (freedomfoodView.addRestaurantData(afreedomfood))
            freedomfoods.create(afreedomfood)
        else
            logger.info("Restaurant Not Added")
    }

    private fun list() {
        freedomfoodView.listRestaurants(freedomfoods)
    }

    private fun update() {
        freedomfoodView.listRestaurants(freedomfoods)
        val searchId = freedomfoodView.getId()
        val afreedomfood = search(searchId)

        if(afreedomfood != null) {
            if(freedomfoodView.updateRestaurantData(afreedomfood)) {
                freedomfoods.update(afreedomfood)
                freedomfoodView.showRestaurant(afreedomfood)
                logger.info("Restaurant Updated : [ $afreedomfood ]")
            }
            else
                logger.info("Restaurant Not Updated")
        }
        else
            println("Restaurant Not Updated...")
    }

    private fun search() {
        val afreedomfood = search(freedomfoodView.getId())!!
        freedomfoodView.showRestaurant(afreedomfood)
    }

    private fun search(id: Long): FreedomFoodModel? {
        return freedomfoods.toEdit(id)
    }

    private fun delete() {
        freedomfoodView.listRestaurants(freedomfoods)
        val searchId = freedomfoodView.getId()
        val afreedomfood = search(searchId)

        if(afreedomfood != null) {
            freedomfoods.delete(afreedomfood)
            println("Restaurant Deleted...")
            freedomfoodView.listRestaurants(freedomfoods)
        }
        else
            println("Restaurant Not Deleted...")
    }

    private fun dummyData() {
        freedomfoods.create(FreedomFoodModel(restaurantname = "New York New York", restaurantdescription = "So Good They Named It Twice"))
        freedomfoods.create(FreedomFoodModel(restaurantname= "Ring of Kerry", restaurantdescription = "Some place in the Kingdom"))
        freedomfoods.create(FreedomFoodModel(restaurantname = "Waterford City", restaurantdescription = "You get great Blaas Here!!"))
    }
}