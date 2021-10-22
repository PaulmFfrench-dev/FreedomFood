package org.wit.freedomfood.console.views

import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import org.wit.freedomfood.console.models.FreedomFoodModel
import tornadofx.*

class RestaurantInfoScreen : View("Restaurant Information") {
    val freedomFoodUIController: FreedomFoodUIController by inject()
    private var tableContent = freedomFoodUIController.freedomfoods.findOne(freedomFoodUIController.showdata())
    private val data = tableContent.observable()

    override var root = vbox {
        setPrefSize(840.0, 500.0)
         tableview(data) {
             readonlyColumn("Id",  FreedomFoodModel::id)
             readonlyColumn("Restaurant Name", FreedomFoodModel::restaurantname)
             readonlyColumn("Restaurant Name", FreedomFoodModel::restaurantname)
             readonlyColumn("Restaurant Description", FreedomFoodModel::restaurantdescription)
             readonlyColumn("Rating", FreedomFoodModel::rating)
             readonlyColumn("Meal", FreedomFoodModel::meal)
             readonlyColumn("Allergen Free", FreedomFoodModel::allergenFree)
         }
        button("Back") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    freedomFoodUIController.loadSearchScreenFromInfo()
                }
            }
        }
        button("Return to Main Menu") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    freedomFoodUIController.closeRestaurantInfo()
                }
            }
        }
    }
}

