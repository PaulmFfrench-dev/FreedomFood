package org.wit.freedomfood.console.main

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

var restaurantname = ""
var restaurantdescription = ""

fun main(args: Array<String>){
    logger.info { "Launching FreedomFood Console App" }
    println("FreedomFood Kotlin App Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addRestaurant()
            2 -> updateRestaurant()
            3 -> listRestaurants()
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
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}


fun addRestaurant(){
    println("Add a Restaurant")
    print("\nEnter a Restaurants name : ")
    restaurantname = readLine()!!
    print("Enter a description for the Restaurants : ")
    restaurantdescription = readLine()!!

    println("You entered $restaurantname for the name and $restaurantdescription for the Restaurants description")
}

fun updateRestaurant() {
    println("Update Restaurant")

    print("\nEnter a new Name for [ $restaurantname ] : ")
    restaurantname = readLine()!!
    print("Enter a new Description for [ $restaurantdescription ] : ")
    restaurantdescription = readLine()!!
    println("You updated [ $restaurantname ] for title and [ $restaurantdescription ] for description")
}

fun listRestaurants() {
    println("You Chose List All Restaurants")
}