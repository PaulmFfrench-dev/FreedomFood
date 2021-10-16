package org.wit.freedomfood.console.views

import org.wit.freedomfood.console.models.FreedomFoodMemStore
import org.wit.freedomfood.console.models.FreedomFoodModel

class FreedomFoodView {

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

    fun listRestaurants(freedomfoods : FreedomFoodMemStore) {
        println("List All Restaurants")
        println()
        freedomfoods.logAll()
        println()
    }

    fun showRestaurant(freedomfood : FreedomFoodModel) {
        if(freedomfood != null)
            println("Restaurant Details [ $freedomfood ]")
        else
            println("Restaurant Not Found...")
    }

    fun addRestaurantData(freedomfood : FreedomFoodModel) : Boolean {

        println()
        print("Enter a Title : ")
        freedomfood.restaurantname = readLine()!!
        print("Enter a Description : ")
        freedomfood.restaurantdescription = readLine()!!

        return freedomfood.restaurantname.isNotEmpty() && freedomfood.restaurantdescription.isNotEmpty()
    }

    fun updateRestaurantData(freedomfood : FreedomFoodModel) : Boolean {

        var tempTitle: String?
        var tempDescription: String?

        if (freedomfood != null) {
            print("Enter a new Title for [ " + freedomfood.restaurantname + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + freedomfood.restaurantdescription + " ] : ")
            tempDescription = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                freedomfood.restaurantname = tempTitle
                freedomfood.restaurantdescription = tempDescription
                return true
            }
        }
        return false
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
}