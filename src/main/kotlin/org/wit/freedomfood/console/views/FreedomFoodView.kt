package org.wit.freedomfood.console.views

import org.wit.freedomfood.console.models.FreedomFoodJSONStore
import org.wit.freedomfood.console.models.FreedomFoodModel

class FreedomFoodView {

    fun menu() : Int {

        val option : Int

        println("Main Menu")
        println(" 1. Add a Restaurant")
        println(" 2. Update a Restaurant")
        println(" 3. List All Restaurants")
        println(" 4. Search Restaurants")
        println(" 5. Delete Restaurants")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        val input: String? = readLine()!!
        option = if (input?.toIntOrNull() != null && input.isNotEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listRestaurants(freedomfoods : FreedomFoodJSONStore) {
        println("List All Restaurants")
        println()
        freedomfoods.logAll()
        println()
    }

    fun showRestaurant(freedomfood : FreedomFoodModel) {
        println("Restaurant Details [ $freedomfood ]")
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

        print("Enter a new Title for [ " + freedomfood.restaurantname + " ] : ")
        val tempTitle: String? = readLine()!!
        print("Enter a new Description for [ " + freedomfood.restaurantdescription + " ] : ")
        val tempDescription: String? = readLine()!!

        if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
            freedomfood.restaurantname = tempTitle
            freedomfood.restaurantdescription = tempDescription
            return true
        }
        return false
    }

    fun getId() : Long {
        val searchId : Long // Long to hold converted id
        print("Enter id to Search/Update/Delete : ")
        val strId : String? = readLine()!! // String to hold user input
        searchId = if (strId?.toLongOrNull() != null && strId.isNotEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}