package org.wit.freedomfood.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import org.wit.freedomfood.console.models.FreedomFoodModel
import tornadofx.*

class SearchFreedomFoodScreen : View("Search for a Restaurant to View") {
    private val model = ViewModel()
    private val _id = model.bind { SimpleStringProperty() }
    val freedomfoodUIController: FreedomFoodUIController by inject()
    private val tableContent = freedomfoodUIController.freedomfoods.findAll()
    private val data = tableContent.observable()

    override var root = form {
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
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        if (freedomfoodUIController.doesSearchExist(_id.value)) {
                            freedomfoodUIController.addSearchData(_id.value)
                            freedomfoodUIController.loadRestaurantInfoScreen()
                        } else
                            freedomfoodUIController.doesNotExist()
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
    override fun onDock() {
        _id.value = ""
        model.clearDecorators()
    }
}
