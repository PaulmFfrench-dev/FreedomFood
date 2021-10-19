package org.wit.freedomfood.console.views

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import jdk.nashorn.internal.runtime.JSType.toLong
import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import tornadofx.*

class UpdateSearchFreedomFoodScreen : View("Search for a Restaurant to Update") {
    val model = ViewModel()
    val _id = model.bind { SimpleIntegerProperty() }
    val freedomfoodUIController: FreedomFoodUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Search by ID") {
                textfield(_id).required()
            }
            button("Search") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomfoodUIController.loadUpdateScreen()
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