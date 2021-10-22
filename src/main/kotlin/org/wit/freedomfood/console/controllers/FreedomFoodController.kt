package org.wit.freedomfood.console.controllers

import mu.KotlinLogging
import org.wit.freedomfood.console.models.FreedomFoodJSONStore
import org.wit.freedomfood.console.models.FreedomFoodModel
import org.wit.freedomfood.console.views.FreedomFoodView

class FreedomFoodController {

    private val freedomfoods = FreedomFoodJSONStore()
    private val freedomfoodView = FreedomFoodView()
    private val logger = KotlinLogging.logger {}

    /**
     * Displays the following strings on app start
     */
    init {
        logger.info { "Launching FreedomFood Console App" }
        println("FreedomFood Kotlin App Version 5.0")
    }

    /**
     * A looping menu on start and ends only on exit
     */
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

    /**
     * Returns the menu from freedomfoodview
     */
    private fun menu() :Int { return freedomfoodView.menu() }

    /**
     * Writes a FreedomFoodModel to the freedomfoods.json file
     */
    private fun add(){
        val afreedomfood = FreedomFoodModel()

        if (freedomfoodView.addRestaurantData(afreedomfood))
            freedomfoods.create(afreedomfood)
        else
            logger.info("Restaurant Not Added")
    }

    /**
     * Reads all restaurants from the freedomfoods.json file
     */
    private fun list() {
        freedomfoodView.listRestaurants(freedomfoods)
    }

    /**
     * Writes an updated FreedomFoodModel to the freedomfoods.json file
     */
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

    /**
     * Displays the data of a single restaurant from the FreedomFoodModel
     */
    private fun search() {
        val afreedomfood = search(freedomfoodView.getId())!!
        freedomfoodView.showRestaurant(afreedomfood)
    }

    /**
     * Returns a single restaurant from the FreedomFoodModel
     */
    private fun search(id: Long): FreedomFoodModel? {
        return freedomfoods.toEdit(id)
    }

    /**
     * Writes to freedomfoods.json file for the deletion of a freedomfoodmodel
     */
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

    /**
     * Writes three FreeFoodModels to the freedomfoods.json
     */
    private fun dummyData() {
        freedomfoods.create(FreedomFoodModel(restaurantname = "Four Star", restaurantdescription = "Excellent Pizzas!", rating = 4, meal = "Vegan Pizza", allergenFree = "No"))
        freedomfoods.create(FreedomFoodModel(restaurantname= "Ephesus", restaurantdescription = "A small but excellent chipper", rating = 5, meal = "Kebab", allergenFree = "Yes"))
        freedomfoods.create(FreedomFoodModel(restaurantname = "Penguin", restaurantdescription = "A fine Chipper!", rating = 4, meal = "Burger", allergenFree = "Yes"))
    }
}