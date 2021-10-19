package org.wit.freedomfood.console.controllers

import mu.KotlinLogging
import org.wit.freedomfood.console.models.FreedomFoodJSONStore
import org.wit.freedomfood.console.models.FreedomFoodModel
import org.wit.freedomfood.console.models.FreedomFoodSearchDataJSONStore
import org.wit.freedomfood.console.models.FreedomFoodSearchDataModel
import org.wit.freedomfood.console.views.*
import tornadofx.*

class FreedomFoodUIController : Controller() {
    val freedomfoodmodel = FreedomFoodModel()
    val freedomfoodView = SearchFreedomFoodScreen()
    val freedomfoods = FreedomFoodJSONStore()
    val freedomfoodsearchdata = FreedomFoodSearchDataJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching FreedomFood TornadoFX UI App" }
    }
    fun add(_title : String, _description : String){
        var afreedomfood = FreedomFoodModel(restaurantname = _title, restaurantdescription = _description)
        freedomfoods.create(afreedomfood)
        logger.info("Restaurant Added")
    }

    fun addSearchData(_id : Long){
        var afreedomfood = FreedomFoodSearchDataModel(id = _id)
        freedomfoodsearchdata.create(afreedomfood)
        logger.info("Search Data Added")
    }

    fun showdata(): FreedomFoodModel? {
        var latestId = freedomfoodsearchdata.findLatest()
        var newid = latestId?.id!!.toLong()
        return search(newid)
    }

    fun update(_title : String, _description : String){
        var afreedomfood = FreedomFoodModel(restaurantname = _title, restaurantdescription = _description)
        freedomfoods.create(afreedomfood)
        logger.info("Restaurant created")
    }

    fun delete(_id: Long){
        val afreedomfood = search(id = _id)
        print(afreedomfood)
        if(afreedomfood != null) {
            freedomfoods.delete(afreedomfood)
            logger.info("Restaurant Deleted...")
            freedomfoods.findAll()
            println("world")
        }
        else
            logger.info("Restaurant Not Deleted...")
            println("hello")
    }

    fun search(id: Long): FreedomFoodModel? {
        return freedomfoods.findOne(id)
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
            find(MenuScreen::class).replaceWith(UpdateSearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
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
            find(MenuScreen::class).replaceWith(SearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
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
