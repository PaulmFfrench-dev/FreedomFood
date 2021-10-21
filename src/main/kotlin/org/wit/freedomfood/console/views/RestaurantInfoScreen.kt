package org.wit.freedomfood.console.views

import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import tornadofx.*

class RestaurantInfoScreen : View("Restaurant Information") {
    val freedomFoodUIController: FreedomFoodUIController by inject()
    var tableContent = freedomFoodUIController.showdata()
    override var root = vbox {
        setPrefSize(1000.0, 400.0)
        setPrefSize(600.0, 200.0)
//        tableview(data) {
//            readonlyColumn("Id", FreedomFoodModel::id)
//            readonlyColumn("Restaurant Name", FreedomFoodModel::restaurantname)
//            readonlyColumn("Restaurant Description", FreedomFoodModel::restaurantdescription)
//        }
        text("Id: "+tableContent?.id.toString())
        text("Name: "+tableContent?.restaurantname.toString())
        text("Description: "+tableContent?.restaurantdescription.toString())
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
    override fun onDock() {
        currentWindow?.setOnCloseRequest {
            println("Closing")
        }
    }
}

