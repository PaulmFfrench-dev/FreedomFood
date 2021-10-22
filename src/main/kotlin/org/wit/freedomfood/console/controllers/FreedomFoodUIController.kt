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
//    private val freedomfoodsearchdatajson = FreedomFoodSearchDataJSONStore()
    private val logger = KotlinLogging.logger {}

    /**
     * String that is to be displayed on app launch
     */
    init {
        logger.info { "Launching FreedomFood TornadoFX UI App" }
    }

    /**
     * Writes a FreedomFoodModel to the freedomsfoods.json File
     */
    fun add(_restaurantname : String, _restaurantdescription : String, _rating : Int, _meal: String, _allergenfree: String){
        val afreedomfood = FreedomFoodModel(restaurantname = _restaurantname,
                                            restaurantdescription = _restaurantdescription,
                                            rating = _rating,
                                            meal = _meal,
                                            allergenFree = _allergenfree)
        freedomfoods.create(afreedomfood)
        logger.info("Restaurant Added")
    }

    /**
     * Writes a searched id to temporary storage
     */
    fun addSearchData(_id : String){
        try {
            val convertedId = _id.toLong()
            val afreedomfood = FreedomFoodSearchDataModel(id = convertedId)
            freedomfoodsearchdata.create(afreedomfood)
            logger.info("\nSearch Data Added : [ $afreedomfood ]")
        }
        catch (e: NumberFormatException) {
            logger.error { "This is not a valid id" }
        }
    }

    /**
     * Returns the latest id that was searched
     */
    fun showdata(): Long {
        val latestId = freedomfoodsearchdata.findLatest()
        return latestId.id
    }

    /**
     * Writes an updated FreedomFoodModel to the freedomfoods.json file
     */
    fun update(_restaurantname : String, _restaurantdescription : String, _rating: Int, _meal: String, _allergenfree: String){
        val latestData = showdata()
        val afreedomfood = FreedomFoodModel(id = latestData,
                                            restaurantname = _restaurantname,
                                            restaurantdescription = _restaurantdescription,
                                            rating = _rating,
                                            meal = _meal,
                                            allergenFree = _allergenfree)
        freedomfoods.update(afreedomfood)
        logger.info("Restaurant Updated : [ $afreedomfood ]")
    }

    /**
     * Writes to freedomfoods.json file for the deletion of a freedomfoodmodel
     */
    fun delete(afreedomfood: FreedomFoodModel?){
        if(afreedomfood != null) {
            freedomfoods.delete(afreedomfood)
            logger.info("Restaurant Deleted...")
        }
        else
            logger.info("Restaurant Not Deleted...")
    }

    /**
     * Returns true or false for if the id given in the search input exists
     */
    fun doesSearchExist(id: String): Boolean {
        try {
            val convertediId = id.toLong()
            if (freedomfoods.toEdit(convertediId) != null) {
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

    /**
     * If doesSearchExist returns false, this function is then called to output a string
     */
    fun doesNotExist() {
        logger.error { "This id does not exist" }
    }

    /**
     * Loads the AddFreedomFoodScreen view from ListFreedomFoodScreen
     */
    fun loadaddFromList() {
        runLater {
            find(ListFreedomFoodScreen::class).replaceWith(AddFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the SearchFreedomFoodScreen view from ListFreedomFoodScreen
     */
    fun loadViewScreenfromList() {
        runLater {
            find(ListFreedomFoodScreen::class).replaceWith(SearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the UpdateSearchFreedomFoodScreen view from ListFreedomFoodScreen
     */
    fun loadUpdateScreenfromList() {
        runLater {
            find(ListFreedomFoodScreen::class).replaceWith(UpdateSearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the DeleteSearchFreedomFoodScreen view from ListFreedomFoodScreen
     */
    fun loadDeleteScreenfromList() {
        runLater {
            find(ListFreedomFoodScreen::class).replaceWith(DeleteSearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the AddFreedomFoodScreen view from MenuScreen
     */
    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the ListFreedomFoodScreen view from MenuScreen
     */
    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        freedomfoods.logAll()
    }

    /**
     * Loads the UpdateSearchFreedomFoodScreen view from MenuScreen
     */
    fun loadUpdateSearchScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(UpdateSearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the UpdateFreedomFoodScreen view from UpdateSearchFreedomFoodScreen
     */
    fun loadUpdateScreen() {
        runLater {
            find(UpdateSearchFreedomFoodScreen::class).replaceWith(UpdateFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the UpdateSearchFreedomFoodScreen view from UpdateFreedomFoodScreen
     */
    fun loadUpdateSearchScreenFromInfo() {
        runLater {
            find(UpdateFreedomFoodScreen::class).replaceWith(UpdateSearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the DeleteSearchFreedomFoodScreen view from MenuScreen
     */
    fun loadDeleteSearchScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(DeleteSearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the DeleteSearchFreedomFoodScreen view from DeleteFreedomFoodScreen
     */
    fun loadDeleteSearchScreenFromDelete() {
        runLater {
            find(DeleteFreedomFoodScreen::class).replaceWith(DeleteSearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the DeleteFreedomFoodScreen view from DeleteSearchFreedomFoodScreen
     */
    fun loadDeleteScreen() {
        runLater {
            find(DeleteSearchFreedomFoodScreen::class).replaceWith(DeleteFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the SearchFreedomFoodScreen view from MenuScreen
     */
    fun loadSearchScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(SearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the SearchFreedomFoodScreen view from RestaurantInfoScreen
     */
    fun loadSearchScreenFromInfo() {
        runLater {
            find(RestaurantInfoScreen::class).replaceWith(SearchFreedomFoodScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the RestaurantInfoScreen view from SearchFreedomFoodScreen
     */
    fun loadRestaurantInfoScreen() {
        runLater {
            find(SearchFreedomFoodScreen::class).replaceWith(RestaurantInfoScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the MenuScreen view from AddFreedomFoodScreen
     */
    fun closeAdd() {
        runLater {
            find(AddFreedomFoodScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the MenuScreen view from ListFreedomFoodScreen
     */
    fun closeList() {
        runLater {
            find(ListFreedomFoodScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the MenuScreen view from UpdateFreedomFoodScreen
     */
    fun closeUpdate() {
        runLater {
            find(UpdateFreedomFoodScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the MenuScreen view from DeleteFreedomFoodScreen
     */
    fun closeDelete() {
        runLater {
            find(DeleteFreedomFoodScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the MenuScreen view from DeleteSearchFreedomFoodScreen
     */
    fun closeDeleteSearch() {
        runLater {
            find(DeleteSearchFreedomFoodScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the MenuScreen view from SearchFreedomFoodScreen
     */
    fun closeSearch() {
        runLater {
            find(SearchFreedomFoodScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the MenuScreen view from RestaurantInfoScreen
     */
    fun closeRestaurantInfo() {
        runLater {
            find(RestaurantInfoScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    /**
     * Loads the MenuScreen view from UpdateSearchFreedomFoodScreen
     */
    fun closeUpdateSearch() {
        runLater {
            find(UpdateSearchFreedomFoodScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
}
