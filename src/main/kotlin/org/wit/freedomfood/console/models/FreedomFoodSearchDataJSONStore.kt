package org.wit.freedomfood.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

import org.wit.freedomfood.console.helpers.*
import java.util.*

const val JSONData_FILE = "freedomfoodsearchdata.json"
val DatagsonBuilder = GsonBuilder().setPrettyPrinting().create()!!
val DatalistType = object : TypeToken<ArrayList<FreedomFoodSearchDataModel>>() {}.type!!

class FreedomFoodSearchDataJSONStore : FreedomFoodSearchDataStore {

    private var freedomfoods = mutableListOf<FreedomFoodSearchDataModel>()

    init {
        if (exists(JSONData_FILE)) {
            deserialize()
        }
    }

    /**
     * Returns the last record from the FreedomFoodSearchDataModel
     */
    override fun findLatest(): FreedomFoodSearchDataModel {
        return freedomfoods.last()
    }

    /**
     * Writes a FreedomFoodSearchDataModel to the freedomfoodsearchdata.json
     */
    override fun create(freedomfood: FreedomFoodSearchDataModel) {
        freedomfood.id = freedomfood.id
        freedomfoods.add(freedomfood)
        serialize()
    }

    /**
     * Writes to the JSON file
     */
    private fun serialize() {
        val jsonString = DatagsonBuilder.toJson(freedomfoods, DatalistType)
        write(JSONData_FILE, jsonString)
    }

    /**
     * Reads from the JSON file
     */
    private fun deserialize() {
        val jsonString = read(JSONData_FILE)
        freedomfoods = Gson().fromJson(jsonString, DatalistType)
    }
}