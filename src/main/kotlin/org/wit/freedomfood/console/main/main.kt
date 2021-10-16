package org.wit.freedomfood.console.main

import mu.KotlinLogging
import org.wit.freedomfood.console.models.FreedomFoodModel

private val logger = KotlinLogging.logger {}

var freedomfood = FreedomFoodModel()
val freedomfoods = ArrayList<FreedomFoodModel>()

fun main(args: Array<String>){
    logger.info { "Launching FreedomFood Console App" }
    println("FreedomFood Kotlin App Version 2.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addRestaurant()
            2 -> updateRestaurant()
            3 -> listRestaurants()
            4 -> searchRestaurants()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down FreedomFood Console App" }
}

fun menu() : Int {

    var option : Int
    var input: String? = null

    println("Main Menu")
    println(" 1. Add a Restaurant")
    println(" 2. Update a Restaurant")
    println(" 3. List All Restaurants")
    println(" 4. Search Restaurants")
    println("-1. Exit")
    println()
    print("Enter Option : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}


fun addRestaurant(){
    var afreedomfood = FreedomFoodModel()
    println("Add a Restaurant")
    println()
    print("Enter a Restaurants name : ")
    afreedomfood.restaurantname = readLine()!!
    print("Enter a description for the Restaurants : ")
    afreedomfood.restaurantdescription = readLine()!!

    if (afreedomfood.restaurantname.isNotEmpty() && afreedomfood.restaurantdescription.isNotEmpty()) {
        afreedomfood.id = freedomfoods.size.toLong()
        freedomfoods.add(afreedomfood.copy())
        logger.info("Restaurant Added : [ $afreedomfood ]")
    }
    else
        logger.info("Restaurant Not Added")
}

fun updateRestaurant() {
    println("Update Restaurant")
    println()
    listRestaurants()
    var searchId = getId()
    val afreedomfood = search(searchId)

    if(afreedomfood !=null) {
        print("Enter a new Name for [ " + afreedomfood.restaurantname + " ] : ")
        afreedomfood.restaurantname = readLine()!!
        print("Enter a new Description for [ " + afreedomfood.restaurantdescription + " ] : ")
        afreedomfood.restaurantdescription = readLine()!!
        println(
            "You updated [ " + afreedomfood.restaurantname + " ] for the name " +
                    "and [ " + afreedomfood.restaurantdescription + " ] for the description"
        )
    }
    else
        println("Restaruant Not Updated...")
}

fun listRestaurants() {
    println("List All Restaurants")
    println()
    freedomfoods.forEach { logger.info("${it}") }
    println()
}

fun searchRestaurants() {
    var searchId = getId()
    val afreedomfood = search(searchId)

    if(afreedomfood != null)
        println("Restaurant Details [ $afreedomfood ]")
    else
        println("Restaurant Not Found...")
}

fun getId() : Long {
    var strId : String? // String to hold user input
    var searchId : Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : FreedomFoodModel? {
    var foundFreedomFood: FreedomFoodModel? = freedomfoods.find { p -> p.id == id }
    return foundFreedomFood
}

fun dummyData() {
    freedomfoods.add(FreedomFoodModel(1, "The Swan", "Chinese Food"))
    freedomfoods.add(FreedomFoodModel(2, "The Penguin", "Chipper Food"))
    freedomfoods.add(FreedomFoodModel(3, "Ephesus", "Chipper Food"))
}