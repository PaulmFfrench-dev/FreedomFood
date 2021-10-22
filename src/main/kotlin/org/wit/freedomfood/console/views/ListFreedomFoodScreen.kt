package org.wit.freedomfood.console.views

import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import org.wit.freedomfood.console.models.FreedomFoodModel
import tornadofx.*

class ListFreedomFoodScreen : View("List Restaurants") {
    val freedomFoodUIController: FreedomFoodUIController by inject()
    private val tableContent = freedomFoodUIController.freedomfoods.findAll()
    private val data = tableContent.observable()

    override val root = vbox {
        setPrefSize(820.0, 600.0)
        tableview(data) {
                readonlyColumn("Id", FreedomFoodModel::id)
                readonlyColumn("Restaurant Name", FreedomFoodModel::restaurantname)
                readonlyColumn("Restaurant Description", FreedomFoodModel::restaurantdescription)
                readonlyColumn("Rating", FreedomFoodModel::rating)
                column("Action", FreedomFoodModel::id).cellFormat {
                    graphic = hbox(spacing = 5) {
                        button("View").action { freedomFoodUIController.loadViewScreenfromList() }
                        button("Edit").action { freedomFoodUIController.loadUpdateScreenfromList() }
                        button("Delete").action { freedomFoodUIController.loadDeleteScreenfromList() }
                    }
                }
        }
        button("Return to Main Menu") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    freedomFoodUIController.closeList()
                }
            }
        }
    }
}