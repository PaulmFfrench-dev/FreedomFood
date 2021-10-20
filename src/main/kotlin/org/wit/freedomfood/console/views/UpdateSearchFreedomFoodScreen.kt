package org.wit.freedomfood.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import tornadofx.*

class UpdateSearchFreedomFoodScreen : View("Search for a Restaurant to Update") {
    private val model = ViewModel()
    private val _id = model.bind { SimpleStringProperty() }
    val freedomfoodUIController: FreedomFoodUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
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
                        if (freedomfoodUIController.doesSearchExist(_id.value.toLong())) {
                            freedomfoodUIController.addSearchData(_id.value.toLong())
                            freedomfoodUIController.loadUpdateScreen()
                        }
                        else
                            freedomfoodUIController.doesNotExist()
                    }
                }
            }
            button("Return to Main Menu") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomfoodUIController.closeUpdateSearch()
                    }
                }
            }
        }
    }
}