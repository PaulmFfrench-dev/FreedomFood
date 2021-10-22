package org.wit.freedomfood.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import javafx.scene.control.TableCell
import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import org.wit.freedomfood.console.models.FreedomFoodModel
import tornadofx.*
import javax.swing.table.TableColumn

class DeleteSearchFreedomFoodScreen : View("Search for a Restaurant to Delete") {
    private val model = ViewModel()
    private val _id = model.bind { SimpleStringProperty() }
    val freedomfoodUIController: FreedomFoodUIController by inject()
    private val tableContent = freedomfoodUIController.freedomfoods.findAll()
    private val data = tableContent.observable()

    override val root = form {
        setPrefSize(600.0, 600.0)
        tableview(data) {
            readonlyColumn("Id", FreedomFoodModel::id)
            readonlyColumn("Restaurant Name", FreedomFoodModel::restaurantname)
            readonlyColumn("Restaurant Description", FreedomFoodModel::restaurantdescription)
            readonlyColumn("Rating", FreedomFoodModel::rating)
            readonlyColumn("Meal", FreedomFoodModel::meal)
            readonlyColumn("Allergen Free", FreedomFoodModel::allergenFree)
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
                        if (freedomfoodUIController.doesSearchExist(_id.value)) {
                            freedomfoodUIController.addSearchData(_id.value)
                            freedomfoodUIController.loadDeleteScreen()
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
                        freedomfoodUIController.closeDeleteSearch()
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