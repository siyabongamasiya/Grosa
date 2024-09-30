package Ecommerce.project.grosa.Utils

import Ecommerce.project.grosa.Domain.Model.Room.GroceryItemRoom
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListConverter {

    @TypeConverter
    fun fromStringArrayList(value: List<GroceryItemRoom>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toStringArrayList(value: String): List<GroceryItemRoom> {
        return try {
            val type = object : TypeToken<List<GroceryItemRoom>>(){}.type
            Gson().fromJson(value,type) //using extension function
        } catch (e: Exception) {
            listOf()
        }
    }
}