package org.wit.freedomfood.console.views

import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import org.wit.freedomfood.console.models.FreedomFoodModel
import tornadofx.*

class DeleteFreedomFoodScreen : View("Delete a Restaurant") {
    val freedomFoodUIController: FreedomFoodUIController by inject()
    var tableContent = freedomFoodUIController.freedomfoods.findOne(freedomFoodUIController.showdata()!!)
    var toDelete = freedomFoodUIController.freedomfoods.toEdit(freedomFoodUIController.showdata()!!)
    private val data = tableContent.observable()

    override var root = vbox {
        setPrefSize(600.0, 600.0)
        tableview(data) {
            readonlyColumn("Id",  FreedomFoodModel::id)
            readonlyColumn("Restaurant Name", FreedomFoodModel::restaurantname)
            readonlyColumn("Restaurant Description", FreedomFoodModel::restaurantdescription)
            readonlyColumn("Rating", FreedomFoodModel::rating)
            readonlyColumn("Meal", FreedomFoodModel::meal)
            readonlyColumn("Allergen Free", FreedomFoodModel::allergenFree)
        }
        button("Delete") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    freedomFoodUIController.delete(toDelete)
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