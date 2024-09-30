package Ecommerce.project.grosa.Data.Room

import Ecommerce.project.grosa.Domain.Model.Room.GroceryItemRoom
import Ecommerce.project.grosa.Domain.Model.Room.GroceryRoom
import Ecommerce.project.grosa.Domain.Model.Room.GroceryWithItems
import Ecommerce.project.grosa.Domain.Model.Room.groceryAndItemCross
import Ecommerce.project.grosa.Utils.ListConverter
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.Transaction
import androidx.room.TypeConverters
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceriesDAO {


    //Create

    /**
     * 1.saves successfully
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGrocery(groceryRoom: GroceryRoom)

    /**
     * 1.saves successfully
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGroceryItem(groceryItemRoom: GroceryItemRoom)

    /**
     * 1.saves successfully
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveGroceryAndItemCross(groceryAndItemCross: groceryAndItemCross)



    //Read

    /**
     * 1.gets correct grocery by name
     * 2.returns nothing if there is no match
     */
    @Query("SELECT * FROM GroceryRoom WHERE name = :groceryName")
    fun getGroceryByName(groceryName : String) : GroceryRoom?

    /**
     * 1.get full list of groceries from room
     * 2.no duplicates i.e replaced if same item  is saved initially
     */

    @Transaction
    @Query("SELECT * FROM GroceryRoom")
    fun getGroceries() : Flow<List<GroceryWithItems>>


    //delete

    /**
     * 1. successfully deletes
     */
    @Delete
    suspend fun deleteGroceryItem(groceryItemRoom: GroceryItemRoom)


    /**
     * 1. successfully deletes
     */
    @Delete
    suspend fun deleteGroceryAndItemCross(groceryAndItemCross: groceryAndItemCross)


    /**
     * 1. successfully deletes
     */
    @Delete
    suspend fun deleteGrocery(groceryRoom: GroceryRoom)
}



@Database(entities = [GroceryRoom::class,GroceryItemRoom::class,groceryAndItemCross::class], version = 1)
@TypeConverters(ListConverter::class)
abstract class GroceriesDatabase : RoomDatabase(){
    abstract fun getGroceriesDao() : GroceriesDAO
}