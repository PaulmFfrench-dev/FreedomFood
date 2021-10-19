package org.wit.freedomfood.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import tornadofx.*

class SearchFreedomFoodScreen : View("Search for a Restaurant to View") {
    val model = ViewModel()
    val _id = model.bind { SimpleStringProperty() }
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
                        freedomfoodUIController.addSearchData(_id.value.toLong())!!
                        freedomfoodUIController.loadRestaurantInfoScreen()
                    }
                }
            }
            button("Return to Main Menu") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomfoodUIController.closeSearch()
                    }
                }
            }
        }
    }
}
