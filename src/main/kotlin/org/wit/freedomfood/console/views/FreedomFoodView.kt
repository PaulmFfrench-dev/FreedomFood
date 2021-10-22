package org.wit.freedomfood.console.views

import org.wit.freedomfood.console.models.FreedomFoodJSONStore
import org.wit.freedomfood.console.models.FreedomFoodModel

class FreedomFoodView {

    /**
     * Menu for Freedom Food console app for displaying options and reading inputs
     */
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

    /**
     * Read all restaurants from freedomfoodjsonstore and displays them in the terminal via logAll()
     */
    fun listRestaurants(freedomfoods : FreedomFoodJSONStore) {
        println("List All Restaurants")
        println()
        freedomfoods.logAll()
        println()
    }

    /**
     * Displays the details of a single record in FreedomFoodModel
     */
    fun showRestaurant(freedomfood : FreedomFoodModel) {
        println("Restaurant Details [ $freedomfood ]")
    }

    /**
     * Add restaurant data to the FreedomFoodModel
     */
    fun addRestaurantData(freedomfood : FreedomFoodModel) : Boolean {

        println()
        print("Enter a restaurant name : ")
        freedomfood.restaurantname = readLine()!!
        print("Enter a restaurant description : ")
        freedomfood.restaurantdescription = readLine()!!
        print("Enter a restaurant rating : ")
        freedomfood.rating = readLine()!!.toInt()
        print("Enter a meal : ")
        freedomfood.meal = readLine()!!
        print("Enter if the meal is allergen free : ")
        freedomfood.allergenFree = readLine()!!
        return freedomfood.restaurantname.isNotEmpty() &&
                freedomfood.restaurantdescription.isNotEmpty() &&
                freedomfood.rating.toString().isNotEmpty() &&
                freedomfood.meal.isNotEmpty() &&
                freedomfood.allergenFree.isNotEmpty()
    }

    /**
     * Updates a record of the FreedomFoodModel
     */
    fun updateRestaurantData(freedomfood : FreedomFoodModel) : Boolean {

        print("Enter a new Title for [ " + freedomfood.restaurantname + " ] : ")
        val tempName: String? = readLine()!!
        print("Enter a new Description for [ " + freedomfood.restaurantdescription + " ] : ")
        val tempDescription: String? = readLine()!!
        print("Enter a new Rating for [ " + freedomfood.rating + " ] : ")
        val tempRating: String? = readLine()!!
        print("Enter a new Meal for [ " + freedomfood.meal + " ] : ")
        val tempMeal: String? = readLine()!!
        print("Enter if the meal is allergen free for [ " + freedomfood.allergenFree + " ] : ")
        val tempAllergens: String? = readLine()!!

        if (!tempName.isNullOrEmpty() && !tempDescription.isNullOrEmpty() && !tempRating.isNullOrEmpty()
            && !tempMeal.isNullOrEmpty() && !tempAllergens.isNullOrEmpty()) {
            freedomfood.restaurantname = tempName
            freedomfood.restaurantdescription = tempDescription
            freedomfood.rating = tempRating.toInt()
            freedomfood.meal = tempMeal
            freedomfood.allergenFree = tempAllergens
            return true
        }
        return false
    }

    /**
     * Returns a searched Id of type long
     */
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