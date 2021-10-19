package org.wit.freedomfood.console.views

import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import org.wit.freedomfood.console.models.FreedomFoodModel
import tornadofx.*

class ListFreedomFoodScreen : View("List Restaurants") {
    val FreedomFoodUIController: FreedomFoodUIController by inject()
    val tableContent = FreedomFoodUIController.freedomfoods.findAll()
    val data = tableContent.observable()

    override val root = vbox {
        setPrefSize(1000.0, 400.0)
        setPrefSize(600.0, 200.0)
        tableview(data) {
                readonlyColumn("Id", FreedomFoodModel::id)
                readonlyColumn("Restaurant Name", FreedomFoodModel::restaurantname)
                readonlyColumn("Restaurant Description", FreedomFoodModel::restaurantdescription)
        }
        button("Return to Main Menu") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    FreedomFoodUIController.closeList()
                }
            }
        }
    }
}