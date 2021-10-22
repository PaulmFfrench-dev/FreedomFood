package org.wit.freedomfood.console.views

import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import org.wit.freedomfood.console.models.FreedomFoodModel
import tornadofx.*

class ListFreedomFoodScreen : View("List Restaurants") {
    val freedomFoodUIController: FreedomFoodUIController by inject()
    private val tableContent = freedomFoodUIController.freedomfoods.findAll()
    private val data = tableContent.observable()

    override val root = vbox {
        setPrefSize(980.0, 400.0)
        tableview(data) {
                readonlyColumn("Id", FreedomFoodModel::id)
                readonlyColumn("Restaurant Name", FreedomFoodModel::restaurantname)
                readonlyColumn("Restaurant Description", FreedomFoodModel::restaurantdescription)
                readonlyColumn("Rating", FreedomFoodModel::rating)
                readonlyColumn("Meal", FreedomFoodModel::meal)
                readonlyColumn("Allergen Free", FreedomFoodModel::allergenFree)
                column("Action", FreedomFoodModel::id).cellFormat {
                    graphic = hbox(spacing = 5) {
                        button("View") {
                            useMaxWidth = true
                            style = "-fx-background-color: skyblue"
                            action {
                                runAsyncWithProgress {
                                    freedomFoodUIController.loadViewScreenfromList()
                                }
                            }
                        }
                        button("Edit") {
                            useMaxWidth = true
                            style = "-fx-background-color: yellow"
                            action {
                                runAsyncWithProgress {
                                    freedomFoodUIController.loadUpdateScreenfromList()
                                }
                            }
                        }
                        button("Delete") {
                            useMaxWidth = true
                            style = "-fx-background-color: red"
                            action {
                                runAsyncWithProgress {
                                    freedomFoodUIController.loadDeleteScreenfromList()
                                }
                            }
                        }
                    }
                }
        }
        button("Add a Restaurant") {
            useMaxWidth = true
            style = "-fx-background-color: lightgreen"
            action {
                runAsyncWithProgress {
                    freedomFoodUIController.loadaddFromList()
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