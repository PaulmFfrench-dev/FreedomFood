package org.wit.freedomfood

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

fun main(args: Array<String>){
    logger.info { "Launching FreedomFood Console App" }
    println("FreedomFood Kotlin App Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> println("You Chose Add Restaurant")
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