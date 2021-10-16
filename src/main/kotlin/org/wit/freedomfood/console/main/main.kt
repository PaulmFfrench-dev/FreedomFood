package org.wit.freedomfood.console.main

import mu.KotlinLogging
import org.wit.freedomfood.console.models.FreedomFoodMemStore
import org.wit.freedomfood.console.models.FreedomFoodModel
import org.wit.freedomfood.console.views.FreedomFoodView

private val logger = KotlinLogging.logger {}

val freedomfoods = FreedomFoodMemStore()
val freedomfoodView = FreedomFoodView()

fun main(args: Array<String>){
    logger.info { "Launching FreedomFood Console App" }
    println("FreedomFood Kotlin App Version 3.0")

    var input: Int

    do {
        input = freedomfoodView.menu()
        when(input) {
            1 -> addRestaurant()
            2 -> updateRestaurant()
            3 -> freedomfoodView.listRestaurants(freedomfoods)
            4 -> searchRestaurants()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down FreedomFood Console App" }
}

fun addRestaurant(){
    var afreedomfood = FreedomFoodModel()

    if(freedomfoodView.addRestaurantData(afreedomfood))
        freedomfoods.create(afreedomfood)
    else
        logger.info("Restaurant Not Added")
}

fun updateRestaurant() {

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

fun searchRestaurants() {
    val afreedomfood = search(freedomfoodView.getId())!!
    freedomfoodView.showRestaurant(afreedomfood)
}

fun search(id: Long) : FreedomFoodModel? {
    var foundFreedomFood = freedomfoods.findOne(id)
        return foundFreedomFood
}

fun dummyData() {
    freedomfoods.create(FreedomFoodModel(1, "The Swan test", "Chinese Food test"))
    freedomfoods.create(FreedomFoodModel(2, "The Penguin test", "Chipper Food test"))
    freedomfoods.create(FreedomFoodModel(3, "Ephesus test", "Chipper Food test"))
}