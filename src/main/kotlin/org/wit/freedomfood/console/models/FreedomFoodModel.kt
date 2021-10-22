package org.wit.freedomfood.console.models

data class FreedomFoodModel(var id: Long = 0,
                            var restaurantname: String = "",
                            var restaurantdescription: String = "",
                            var rating: Int = 1,
                            var meal: String = "",
                            var allergenFree: String = "")