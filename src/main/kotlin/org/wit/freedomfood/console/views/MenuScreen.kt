package org.wit.freedomfood.console.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.freedomfood.console.controllers.FreedomFoodUIController
import tornadofx.*

class MenuScreen : View("FreedomFood Main Menu") {

    val freedomfoodUIController: FreedomFoodUIController by inject()

    override val root = form {
        setPrefSize(400.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add a Restaurant") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomfoodUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Restaurants") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomfoodUIController.loadListScreen()
                    }
                }
            }
            text("")
            button("Update Restaurants") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomfoodUIController.loadUpdateSearchScreen()
                    }
                }
            }
            text("")
            button("Delete Restaurants") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomfoodUIController.loadDeleteScreen()
                    }
                }
            }
            text("")
            button("Search Restaurants") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        freedomfoodUIController.loadSearchScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }


}