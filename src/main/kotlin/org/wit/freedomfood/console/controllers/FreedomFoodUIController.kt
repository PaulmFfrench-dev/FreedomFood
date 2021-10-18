package org.wit.freedomfood.console.controllers

import javafx.beans.property.SimpleIntegerProperty
import mu.KotlinLogging
import org.wit.freedomfood.console.models.FreedomFoodJSONStore
import org.wit.freedomfood.console.models.FreedomFoodModel
import org.wit.freedomfood.console.views.*
import tornadofx.*

class FreedomFoodUIController : Controller() {

    val freedomfoods = FreedomFoodJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Placemark TornadoFX UI App" }
    }
    fun add(_title : String, _description : String){

        var afreedomfood = FreedomFoodModel(restaurantname = _title, restaurantdescription = _description)
        freedomfoods.create(afreedomfood)
        logger.info("Restaurant Added")
    }
    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        freedomfoods.logAll()
    }
    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeAdd() {
        runLater {
            find(AddFreedomFoodScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListFreedomFoodScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

}