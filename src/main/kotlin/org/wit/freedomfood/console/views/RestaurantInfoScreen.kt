package org.wit.freedomfood.console.views

import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import org.wit.freedomfood.console.models.FreedomFoodModel
import tornadofx.*

class RestaurantInfoScreen : View("Restaurant Information") {
    val freedomFoodUIController: FreedomFoodUIController by inject()
    var tableContent = freedomFoodUIController.freedomfoods.findOne(freedomFoodUIController.showdata())

    private val data = tableContent.observable()

    override var root = vbox {
        setPrefSize(600.0, 600.0)
         tableview(data) {
             column("Id",  FreedomFoodModel::id)
             column("Restaurant Name", FreedomFoodModel::restaurantname)
             readonlyColumn("Restaurant Name", FreedomFoodModel::restaurantname)
             column("Restaurant Description", FreedomFoodModel::restaurantdescription)
             column("Rating", FreedomFoodModel::rating)
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

