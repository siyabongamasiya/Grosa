package Ecommerce.project.grosa.Utils

import Ecommerce.project.grosa.Domain.Model.Room.GroceryItemRoom

/**
 * 1.returns the correct total price
 * 2.returns 0 if list is empty
 */
fun calculateTotalPrice(list : List<GroceryItemRoom>) : Int{
    var total = 0

    list.forEach { groceryitem ->
        total = total.plus(groceryitem.itemPrice.toInt())
    }

    return total
}