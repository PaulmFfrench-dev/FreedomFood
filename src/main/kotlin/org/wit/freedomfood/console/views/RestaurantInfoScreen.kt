package org.wit.freedomfood.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import tornadofx.*

class RestaurantInfoScreen : View("Restaurant Information") {
    val model = ViewModel()
    val _title = model.bind { SimpleStringProperty() }
    val _description = model.bind { SimpleStringProperty() }
    val freedomfoodUIController: FreedomFoodUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Title") {
                textfield(_title).required()
            }
            field("Description") {
                textarea(_description).required()
            }
            button("Back") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomfoodUIController.loadSearchScreenFromInfo()
                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomfoodUIController.closeRestaurantInfo()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _title.value = ""
        _description.value = ""
        model.clearDecorators()
    }
}