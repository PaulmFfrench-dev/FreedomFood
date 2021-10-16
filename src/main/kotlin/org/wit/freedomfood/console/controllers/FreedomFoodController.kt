package org.wit.freedomfood.console.controllers

import mu.KotlinLogging
import org.wit.freedomfood.console.models.FreedomFoodMemStore
import org.wit.freedomfood.console.models.FreedomFoodModel
import org.wit.freedomfood.console.views.FreedomFoodView

class FreedomFoodController {

    val freedomfoods = FreedomFoodMemStore()
    val freedomfoodView = FreedomFoodView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching FreedomFood Console App" }
        println("FreedomFood Kotlin App Version 3.0")
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
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down FreedomFood Console App" }
    }

    fun menu() :Int { return freedomfoodView.menu() }

    fun add(){
        var afreedomfood = FreedomFoodModel()

        if (freedomfoodView.addRestaurantData(afreedomfood))
            freedomfoods.create(afreedomfood)
        else
            logger.info("Restaurant Not Added")
    }

    fun list() {
        freedomfoodView.listRestaurants(freedomfoods)
    }

    fun update() {

        freedomfoodView.listRestaurants(freedomfoods)
        var searchId = freedomfoodView.getId()
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

    fun search() {
        val afreedomfood = search(freedomfoodView.getId())!!
        freedomfoodView.showRestaurant(afreedomfood)
    }


    fun search(id: Long) : FreedomFoodModel? {
        var foundRestaurant = freedomfoods.findOne(id)
        return foundRestaurant
    }

    fun dummyData() {
        freedomfoods.create(FreedomFoodModel(restaurantname = "New York New York", restaurantdescription = "So Good They Named It Twice"))
        freedomfoods.create(FreedomFoodModel(restaurantname= "Ring of Kerry", restaurantdescription = "Some place in the Kingdom"))
        freedomfoods.create(FreedomFoodModel(restaurantname = "Waterford City", restaurantdescription = "You get great Blaas Here!!"))
    }
}