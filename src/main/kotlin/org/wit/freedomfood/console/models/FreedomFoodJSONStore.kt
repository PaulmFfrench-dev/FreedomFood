package org.wit.freedomfood.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.freedomfood.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "placemarks.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<FreedomFoodModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class FreedomFoodJSONStore : FreedomFoodStore {

    var freedomfoods = mutableListOf<FreedomFoodModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<FreedomFoodModel> {
        return freedomfoods
    }

    override fun findOne(id: Long) : FreedomFoodModel? {
        var foundRestaurant: FreedomFoodModel? = freedomfoods.find { p -> p.id == id }
        return foundRestaurant
    }

    override fun create(freedomfood: FreedomFoodModel) {
        freedomfood.id = generateRandomId()
        freedomfoods.add(freedomfood)
        serialize()
    }

    override fun update(freedomfood: FreedomFoodModel) {
        var foundRestaurant = findOne(freedomfood.id!!)
        if (foundRestaurant != null) {
            foundRestaurant.restaurantname = foundRestaurant.restaurantname
            foundRestaurant.restaurantdescription = foundRestaurant.restaurantdescription
        }
        serialize()
    }

    internal fun logAll() {
        freedomfoods.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(freedomfoods, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        freedomfoods = Gson().fromJson(jsonString, listType)
    }
}