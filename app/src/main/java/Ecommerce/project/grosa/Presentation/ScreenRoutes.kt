package Ecommerce.project.grosa.Presentation

import Ecommerce.project.grosa.Domain.Model.Grocery
import Ecommerce.project.grosa.Domain.Model.GroceryItem
import kotlinx.serialization.Serializable


sealed class Routes{
    @Serializable
    object HomeRoute

    @Serializable
    data class NewGroceryRoute(val arg : String = "")


    @Serializable
    data class GroceriesRoute(val arg : String = "")


    @Serializable
    data class GroceryItemsList(val grocery:String)
}

