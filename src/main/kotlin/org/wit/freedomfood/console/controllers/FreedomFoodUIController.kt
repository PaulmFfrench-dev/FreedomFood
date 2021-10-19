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
    fun update(_title : String, _description : String){

        var afreedomfood = FreedomFoodModel(restaurantname = _title, restaurantdescription = _description)
        freedomfoods.create(afreedomfood)
        logger.info("Restaurant Added")
    }
    fun delete(_title : String, _description : String){

        var afreedomfood = FreedomFoodModel(restaurantname = _title, restaurantdescription = _description)
        freedomfoods.create(afreedomfood)
        logger.info("Restaurant Added")
    }
    fun search(_title : String, _description : String){

        var afreedomfood = FreedomFoodModel(restaurantname = _title, restaurantdescription = _description)
        freedomfoods.create(afreedomfood)
        logger.info("Restaurant Search")
    }
    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        freedomfoods.logAll()
    }
    fun loadUpdateSearchScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(UpdateFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun loadUpdateScreen() {
        runLater {
            find(UpdateSearchFreedomFoodScreen::class).replaceWith(UpdateFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun loadUpdateSearchScreenFromInfo() {
        runLater {
            find(UpdateFreedomFoodScreen::class).replaceWith(UpdateSearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun loadDeleteScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(DeleteFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun loadSearchScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(UpdateSearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun loadSearchScreenFromInfo() {
        runLater {
            find(RestaurantInfoScreen::class).replaceWith(SearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun loadRestaurantInfoScreen() {
        runLater {
            find(SearchFreedomFoodScreen::class).replaceWith(RestaurantInfoScreen::class, sizeToScene = true, centerOnScreen = true)
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
    fun closeUpdate() {
        runLater {
            find(UpdateFreedomFoodScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeDelete() {
        runLater {
            find(DeleteFreedomFoodScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeSearch() {
        runLater {
            find(SearchFreedomFoodScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeRestaurantInfo() {
        runLater {
            find(RestaurantInfoScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeUpdateSearch() {
        runLater {
            find(UpdateSearchFreedomFoodScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
}