package org.wit.freedomfood.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import org.wit.freedomfood.console.models.FreedomFoodModel
import tornadofx.*

class UpdateSearchFreedomFoodScreen : View("Search for a Restaurant to Update") {
    private val model = ViewModel()
    private val _id = model.bind { SimpleStringProperty() }
    val freedomFoodUIController: FreedomFoodUIController by inject()
    private val tableContent = freedomFoodUIController.freedomfoods.findAll()
    private val data = tableContent.observable()

    override val root = form {
        setPrefSize(600.0, 600.0)
        tableview(data) {
            readonlyColumn("Id", FreedomFoodModel::id)
            readonlyColumn("Restaurant Name", FreedomFoodModel::restaurantname)
            readonlyColumn("Restaurant Description", FreedomFoodModel::restaurantdescription)
            readonlyColumn("Rating", FreedomFoodModel::rating)
        }
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Search by ID") {
                textfield(_id).required()
            }
            button("Search") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        if (freedomFoodUIController.doesSearchExist(_id.value)) {
                            freedomFoodUIController.addSearchData(_id.value)
                            freedomFoodUIController.loadUpdateScreen()
                        }
                        else
                            freedomFoodUIController.doesNotExist()
                    }
                }
            }
            button("Return to Main Menu") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomFoodUIController.closeUpdateSearch()
                    }
                }
            }
        }
    }
    override fun onDock() {
        _id.value = ""
        model.clearDecorators()
    }
}