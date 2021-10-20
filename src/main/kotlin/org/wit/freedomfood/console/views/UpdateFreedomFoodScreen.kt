package org.wit.freedomfood.console.views

import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Orientation
import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import org.wit.freedomfood.console.models.FreedomFoodModel
import tornadofx.*

class UpdateFreedomFoodScreen : View("Update the Restaurant information") {
    val model = ViewModel()
    val _title = model.bind { SimpleStringProperty() }
    val _description = model.bind { SimpleStringProperty() }
    val freedomfoodUIController: FreedomFoodUIController by inject()
    val tableContent = freedomfoodUIController.showdata()

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
               textfield(_title).required()
            }
            field("Description") {
                textarea(_description).required()
            }
            button("Restaurant Update") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomfoodUIController.update(_title.value.toString(),_description.value.toString())
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
        _title.value = ""
        _description.value = ""
        model.clearDecorators()
    }
}