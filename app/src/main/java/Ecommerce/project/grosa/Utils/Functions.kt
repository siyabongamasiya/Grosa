package Ecommerce.project.grosa.Utils

import Ecommerce.project.grosa.Domain.Model.Room.GroceryItemRoom


fun calculateTotalPrice(list : List<GroceryItemRoom>) : Int{
    var total = 0

    list.forEach { groceryitem ->
        total = total.plus(groceryitem.itemPrice.toInt())
    }

    return total
}