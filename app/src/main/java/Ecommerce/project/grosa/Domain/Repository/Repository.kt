package Ecommerce.project.grosa.Domain.Repository

import Ecommerce.project.grosa.Domain.Model.Room.GroceryItemRoom
import Ecommerce.project.grosa.Domain.Model.Room.GroceryRoom
import Ecommerce.project.grosa.Domain.Model.Room.GroceryWithItems
import Ecommerce.project.grosa.Domain.Model.Room.groceryAndItemCross
import kotlinx.coroutines.flow.Flow

interface Repository {

    //Create
    suspend fun saveGrocery(groceryRoom: GroceryRoom)

    suspend fun saveGroceryItem(groceryItemRoom: GroceryItemRoom)

    suspend fun saveGroceryAndItemCross(groceryAndItemCross: groceryAndItemCross)


    //Read
    fun getGroceries() : Flow<List<GroceryWithItems>>

    fun getGroceryByName(groceryName : String) : GroceryRoom?

    //delete
    suspend fun deleteGroceryItem(groceryItemRoom: GroceryItemRoom)

    suspend fun deleteGroceryAndItemCross(groceryAndItemCross: groceryAndItemCross)

    suspend fun deleteGrocery(groceryRoom: GroceryRoom)
}