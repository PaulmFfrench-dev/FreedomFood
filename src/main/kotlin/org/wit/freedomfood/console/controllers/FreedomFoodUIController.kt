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
    val freedomfoods = FreedomFoodJSONStore()
    private val freedomfoodsearchdata = FreedomFoodSearchDataJSONStore()
    private val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching FreedomFood TornadoFX UI App" }
    }
    fun add(_restaurantname : String, _restaurantdescription : String){
        val afreedomfood = FreedomFoodModel(restaurantname = _restaurantname, restaurantdescription = _restaurantdescription)
        freedomfoods.create(afreedomfood)
        logger.info("Restaurant Added")
    }

    fun addSearchData(_id : Long){
        val afreedomfood = FreedomFoodSearchDataModel(id = _id)
        freedomfoodsearchdata.create(afreedomfood)
        logger.info("Search Data Added")
    }

    fun showdata(): FreedomFoodModel? {
        val latestId = freedomfoodsearchdata.findLatest()
        val newid = latestId?.id!!.toLong()
        return search(newid)
    }

    fun update(_restaurantname : String, _restaurantdescription : String){
        val latestData = showdata()
        val afreedomfood = FreedomFoodModel(id = latestData?.id!!,restaurantname = _restaurantname, restaurantdescription = _restaurantdescription)
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
