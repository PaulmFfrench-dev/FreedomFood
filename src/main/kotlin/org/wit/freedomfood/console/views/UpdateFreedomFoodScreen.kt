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
    private val _meal = model.bind { SimpleStringProperty() }
    private val _allergenfree = model.bind { SimpleStringProperty() }

    val freedomFoodUIController: FreedomFoodUIController by inject()
    var tableContent = freedomFoodUIController.freedomfoods.findOne(freedomFoodUIController.showdata()!!)
    var toEdit = freedomFoodUIController.freedomfoods.toEdit(freedomFoodUIController.showdata()!!)
    private val data = tableContent.observable()

    override var root = form {
        setPrefSize(810.0, 550.0)
        tableview(data) {
            readonlyColumn("Id",  FreedomFoodModel::id)
            readonlyColumn("Restaurant Name", FreedomFoodModel::restaurantname)
            readonlyColumn("Restaurant Description", FreedomFoodModel::restaurantdescription)
            readonlyColumn("Rating", FreedomFoodModel::rating)
            readonlyColumn("Meal", FreedomFoodModel::meal)
            readonlyColumn("Allergen Free", FreedomFoodModel::allergenFree)
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
            field("Meal") {
                textfield(_meal).required()
            }
            field("Allergy Free?") {
                textfield(_allergenfree).required()
            }
            button("Restaurant Update") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        if(_rating.value > 5 && _rating.value <= 0 ) {
                            println("Please enter a number between 1 and 5")
                        }
                        if(_allergenfree.value != "no" || _allergenfree.value != "No" ||
                            _allergenfree.value != "yes" || _allergenfree.value != "Yes"){
                            println("Please enter yes or no for if the meal is allergen free")
                        }
                        if(_rating.value < 5 && _rating.value > 0 &&
                            _allergenfree.value.toString() == "no" || _allergenfree.value.toString() == "No" ||
                            _allergenfree.value.toString() == "yes" || _allergenfree.value.toString() == "Yes") {
                            freedomFoodUIController.update(
                                _restaurantname.value,
                                _restaurantdescription.value,
                                _rating.value,
                                _meal.value,
                                _allergenfree.value
                            )
                        }
                            freedomFoodUIController.closeUpdate()
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
        _restaurantname.value = toEdit?.restaurantname
        _restaurantdescription.value = toEdit?.restaurantdescription
        _rating.value = toEdit?.rating
        _meal.value = toEdit?.meal
        _allergenfree.value = toEdit?.allergenFree
        model.clearDecorators()
    }
}