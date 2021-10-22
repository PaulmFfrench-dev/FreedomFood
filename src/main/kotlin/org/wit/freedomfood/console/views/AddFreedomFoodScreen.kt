package org.wit.freedomfood.console.views

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import tornadofx.*

class AddFreedomFoodScreen : View("Add a Restaurant") {
    private val model = ViewModel()
    private val _restaurantname = model.bind { SimpleStringProperty() }
    private val _restaurantdescription = model.bind { SimpleStringProperty() }
    private val _rating = model.bind { SimpleIntegerProperty() }
    val freedomfoodUIController: FreedomFoodUIController by inject()

    override val root = form {
        setPrefSize(600.0, 600.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Restuarant name") {
                textfield(_restaurantname).required()
            }
            field("Restuarant Description") {
                textfield(_restaurantdescription).required()
            }
            field("Rating") {
                textfield(_rating).required()
            }
            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        if(_rating.value < 5 && _rating.value > 0 ) {
                            freedomfoodUIController.add(
                                _restaurantname.value,
                                _restaurantdescription.value,
                                _rating.value
                            )
                            freedomfoodUIController.closeAdd()
                        }
                        else
                            println("Please enter a number between 1 and 5")
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
        _rating.value = 1
        model.clearDecorators()
    }
}