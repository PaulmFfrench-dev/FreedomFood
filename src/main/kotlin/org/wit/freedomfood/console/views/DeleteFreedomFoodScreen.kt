package org.wit.freedomfood.console.views

import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import tornadofx.*

class DeleteFreedomFoodScreen : View("Delete a Restaurant") {
    val freedomFoodUIController: FreedomFoodUIController by inject()
    private val tableContent = freedomFoodUIController.showdata()

    override val root = vbox {
        setPrefSize(1000.0, 400.0)
        setPrefSize(600.0, 200.0)
//        tableview(data) {
//            readonlyColumn("Id", FreedomFoodModel::id)
//            readonlyColumn("Restaurant Name", FreedomFoodModel::restaurantname)
//            readonlyColumn("Restaurant Description", FreedomFoodModel::restaurantdescription)
//        }
        text(tableContent?.id.toString())
        text(tableContent?.restaurantname.toString())
        text(tableContent?.restaurantdescription.toString())
        button("Delete") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    freedomFoodUIController.delete(tableContent)
                    freedomFoodUIController.closeDelete()
                }
            }
        }
        button("Back") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    freedomFoodUIController.loadDeleteSearchScreenFromDelete()
                }
            }
        }
        button("Return to Main Menu") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    freedomFoodUIController.closeDelete()
                }
            }
        }
    }
}