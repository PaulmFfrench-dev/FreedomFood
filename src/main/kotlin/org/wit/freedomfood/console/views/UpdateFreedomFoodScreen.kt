package org.wit.freedomfood.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import tornadofx.*

class UpdateFreedomFoodScreen : View("Update the Restaurant information") {
    private val model = ViewModel()
    private val _restaurantname = model.bind { SimpleStringProperty() }
    private val _restaurantdescription = model.bind { SimpleStringProperty() }
    val freedomfoodUIController: FreedomFoodUIController by inject()
    private val tableContent = freedomfoodUIController.showdata()

    override val root = form {
        setPrefSize(1000.0, 400.0)
        setPrefSize(600.0, 200.0)
//        tableview(data) {
//            readonlyColumn("Id", FreedomFoodModel::id)
//            readonlyColumn("Restaurant Name", FreedomFoodModel::restaurantname)
//            readonlyColumn("Restaurant Description", FreedomFoodModel::restaurantdescription)
//        }
        text("Id: "+tableContent?.id.toString())
        text("Name: "+tableContent?.restaurantname.toString())
        text("Description: "+tableContent?.restaurantdescription.toString())
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Restaurant Name") {
               textfield(_restaurantname).required()
            }
            field("Description") {
                textarea(_restaurantdescription).required()
            }
            button("Restaurant Update") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomfoodUIController.update(_restaurantname.value.toString(),_restaurantdescription.value.toString())
                        freedomfoodUIController.closeUpdate()
                    }
                }
            }
            button("Back") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomfoodUIController.loadUpdateSearchScreenFromInfo()
                    }
                }
            }
            button("Return to Main Menu") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomfoodUIController.closeUpdate()
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