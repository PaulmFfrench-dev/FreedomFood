package org.wit.freedomfood.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import tornadofx.*

class AddFreedomFoodScreen : View("Add a Restaurant") {
    private val model = ViewModel()
    private val _restaurantname = model.bind { SimpleStringProperty() }
    private val _restaurantdescription = model.bind { SimpleStringProperty() }
    val freedomfoodUIController: FreedomFoodUIController by inject()

    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Restuarant name") {
                textfield(_restaurantname).required()
            }
            field("Restuarant Description") {
                textarea(_restaurantdescription).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomfoodUIController.add(_restaurantname.value,_restaurantdescription.value)
                        freedomfoodUIController.closeAdd()
                    }
                }
            }
            button("Return to Main Menu") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomfoodUIController.closeAdd()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _restaurantname.value = ""
        _restaurantdescription.value = ""
        model.clearDecorators()
    }
}