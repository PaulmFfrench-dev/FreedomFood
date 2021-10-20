package org.wit.freedomfood.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.freedomfood.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

const val JSONData_FILE = "freedomfoodsearchdata.json"
val DatagsonBuilder = GsonBuilder().setPrettyPrinting().create()
val DatalistType = object : TypeToken<ArrayList<FreedomFoodSearchDataModel>>() {}.type

class FreedomFoodSearchDataJSONStore : FreedomFoodSearchDataStore {

    private var freedomfoods = mutableListOf<FreedomFoodSearchDataModel>()

    init {
        if (exists(JSONData_FILE)) {
            deserialize()
        }
    }

    override fun findLatest(): FreedomFoodSearchDataModel? {
        return freedomfoods.last()
    }

    override fun create(freedomfood: FreedomFoodSearchDataModel) {
        freedomfood.id = freedomfood.id
        freedomfoods.add(freedomfood)
        serialize()
    }

    private fun serialize() {
        val jsonString = DatagsonBuilder.toJson(freedomfoods, DatalistType)
        write(JSONData_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSONData_FILE)
        freedomfoods = Gson().fromJson(jsonString, DatalistType)
    }
}