package Ecommerce.project.grosa.Presentation

import kotlinx.serialization.Serializable


sealed class Routes{
    @Serializable
    object GroceriesRoute

    @Serializable
    data class NewGroceryRoute(val grocery : String = "")


    @Serializable
    data class GroceryItemsList(val grocery:String)
}

