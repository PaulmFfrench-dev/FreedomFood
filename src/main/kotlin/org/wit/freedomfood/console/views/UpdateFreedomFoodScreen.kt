package org.wit.freedomfood.console.views

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import org.wit.freedomfood.console.models.FreedomFoodModel
import tornadofx.*

class UpdateFreedomFoodScreen : View("Update the Restaurant information") {
    private val model = ViewModel()
    private val _restaurantname = model.bind { SimpleStringProperty() }
    private val _restaurantdescription = model.bind { SimpleStringProperty() }
    private val _rating = model.bind { SimpleIntegerProperty() }

    val freedomFoodUIController: FreedomFoodUIController by inject()
    var tableContent = freedomFoodUIController.freedomfoods.findOne(freedomFoodUIController.showdata()!!)
    private val data = tableContent.observable()

    override var root = form {
        setPrefSize(600.0, 600.0)
        tableview(data) {
            readonlyColumn("Id",  FreedomFoodModel::id)
            readonlyColumn("Restaurant Name", FreedomFoodModel::restaurantname)
            readonlyColumn("Restaurant Description", FreedomFoodModel::restaurantdescription)
            readonlyColumn("Rating", FreedomFoodModel::rating)
        }
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Restaurant Name") {
               textfield(_restaurantname).required()
            }
            field("Description") {
                textfield(_restaurantdescription).required()
            }
            field("Rating") {
                textfield(_rating).required()
            }

            button("Restaurant Update") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        if(_rating.value < 5 && _rating.value > 0 ) {
                            freedomFoodUIController.update(
                                _restaurantname.value,
                                _restaurantdescription.value,
                                _rating.value
                            )
                            freedomFoodUIController.closeUpdate()
                        }
                        else
                            println("Please enter a number between 1 and 5")
                    }
                }
            }
            button("Back") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomFoodUIController.loadUpdateSearchScreenFromInfo()
                    }
                }
            }
            button("Return to Main Menu") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomFoodUIController.closeUpdate()
                    }
                }
            }
        }
    }

    override fun onDock() {
        _restaurantname.value = ""
        _restaurantdescription.value = ""
        _rating.value = 0
        model.clearDecorators()
    }
}