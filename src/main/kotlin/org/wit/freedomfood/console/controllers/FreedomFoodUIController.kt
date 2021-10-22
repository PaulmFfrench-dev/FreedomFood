package org.wit.freedomfood.console.controllers

import mu.KotlinLogging
import org.wit.freedomfood.console.models.*
import org.wit.freedomfood.console.views.*
import tornadofx.*
import java.lang.NullPointerException

class FreedomFoodUIController : Controller() {
    val freedomfoodmodel = FreedomFoodModel()
    val freedomfoods = FreedomFoodJSONStore()
    private val freedomfoodsearchdata = FreedomFoodSearchDataMemStore()
    private val freedomfoodsearchdatajson = FreedomFoodSearchDataJSONStore()
    private val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching FreedomFood TornadoFX UI App" }
    }
    fun add(_restaurantname : String, _restaurantdescription : String, _rating : Int, _meal: String, _allergenfree: String){
        val afreedomfood = FreedomFoodModel(restaurantname = _restaurantname,
                                            restaurantdescription = _restaurantdescription,
                                            rating = _rating,
                                            meal = _meal,
                                            allergenFree = _allergenfree)
        freedomfoods.create(afreedomfood)
        logger.info("Restaurant Added")
    }

    fun addSearchData(_id : String){
        try {
            var convertedId = _id.toLong()
            val afreedomfood = FreedomFoodSearchDataModel(id = convertedId)
            freedomfoodsearchdata.create(afreedomfood)
            logger.info("\nSearch Data Added : [ $afreedomfood ]")
        }
        catch (e: NumberFormatException) {
            logger.error { "This is not a valid id" }
        }
    }

    fun showdata(): Long {
        val latestId = freedomfoodsearchdata.findLatest()
        return latestId.id
    }

    fun update(_restaurantname : String, _restaurantdescription : String, _rating: Int, _meal: String, _allergenfree: String){
        val latestData = showdata()
        val afreedomfood = FreedomFoodModel(id = latestData!!,
                                            restaurantname = _restaurantname,
                                            restaurantdescription = _restaurantdescription,
                                            rating = _rating,
                                            meal = _meal,
                                            allergenFree = _allergenfree)
        freedomfoods.update(afreedomfood)
        logger.info("Restaurant Updated : [ $afreedomfood ]")
    }

    fun delete(afreedomfood: FreedomFoodModel?){
        if(afreedomfood != null) {
            freedomfoods.delete(afreedomfood)
            logger.info("Restaurant Deleted...")
        }
        else
            logger.info("Restaurant Not Deleted...")
    }

    private fun search(id: Long): FreedomFoodModel? {
        return freedomfoods.toEdit(id)
    }

    fun doesSearchExist(id: String): Boolean {
        try {
            var convertediId = id.toLong()
            if (freedomfoods.findOne(convertediId) != null) {
                return true
            }
        }
        catch (e: NumberFormatException) {
            logger.error { "This is not a valid id" }
        }
        catch (e: NullPointerException) {
            doesNotExist()
        }
            return false
    }

    fun doesNotExist() {
        logger.error { "This id does not exist" }
    }
    fun loadViewScreenfromList() {
        runLater {
            find(ListFreedomFoodScreen::class).replaceWith(SearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun loadUpdateScreenfromList() {
        runLater {
            find(ListFreedomFoodScreen::class).replaceWith(UpdateSearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun loadDeleteScreenfromList() {
        runLater {
            find(ListFreedomFoodScreen::class).replaceWith(DeleteSearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
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
    fun loadDeleteSearchScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(DeleteSearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun loadDeleteSearchScreenFromDelete() {
        runLater {
            find(DeleteFreedomFoodScreen::class).replaceWith(DeleteSearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun loadDeleteScreen() {
        runLater {
            find(DeleteSearchFreedomFoodScreen::class).replaceWith(DeleteFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
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
    fun closeDeleteSearch() {
        runLater {
            find(DeleteSearchFreedomFoodScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
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
