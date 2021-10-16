package org.wit.freedomfood.console.main

import mu.KotlinLogging
import org.wit.freedomfood.console.controllers.PlacemarkController
import org.wit.freedomfood.console.models.FreedomFoodMemStore
import org.wit.freedomfood.console.views.FreedomFoodView

private val logger = KotlinLogging.logger {}

val freedomfoods = FreedomFoodMemStore()
val freedomfoodView = FreedomFoodView()

fun main(args: Array<String>){
    PlacemarkController().start()
}