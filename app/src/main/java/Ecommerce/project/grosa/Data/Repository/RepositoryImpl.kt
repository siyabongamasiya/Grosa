package Ecommerce.project.grosa.Data.Repository

import Ecommerce.project.grosa.Data.Room.GroceriesDAO
import Ecommerce.project.grosa.Domain.Model.Room.GroceryItemRoom
import Ecommerce.project.grosa.Domain.Model.Room.GroceryRoom
import Ecommerce.project.grosa.Domain.Model.Room.GroceryWithItems
import Ecommerce.project.grosa.Domain.Model.Room.groceryAndItemCross
import Ecommerce.project.grosa.Domain.Repository.Repository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val groceriesDAO: GroceriesDAO) : Repository {

    //Create
    override suspend fun saveGrocery(groceryRoom: GroceryRoom) {
        groceriesDAO.saveGrocery(groceryRoom)
    }

    override suspend fun saveGroceryItem(groceryItemRoom: GroceryItemRoom) {
        groceriesDAO.saveGroceryItem(groceryItemRoom)
    }

    override suspend fun saveGroceryAndItemCross(groceryAndItemCross: groceryAndItemCross) {
        groceriesDAO.saveGroceryAndItemCross(groceryAndItemCross)
    }


    //Read
    override fun getGroceries(): Flow<List<GroceryWithItems>> {
       return groceriesDAO.getGroceries()
    }

    override fun getGroceryByName(groceryName: String): GroceryRoom? {
        return groceriesDAO.getGroceryByName(groceryName)
    }


    //delete
    override suspend fun deleteGroceryItem(groceryItemRoom: GroceryItemRoom) {
        groceriesDAO.deleteGroceryItem(groceryItemRoom)
    }

    override suspend fun deleteGroceryAndItemCross(groceryAndItemCross: groceryAndItemCross) {
        groceriesDAO.deleteGroceryAndItemCross(groceryAndItemCross)
    }

    override suspend fun deleteGrocery(groceryRoom: GroceryRoom) {
        groceriesDAO.deleteGrocery(groceryRoom)
    }
}