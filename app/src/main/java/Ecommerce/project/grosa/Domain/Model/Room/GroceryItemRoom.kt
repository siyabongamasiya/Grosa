package Ecommerce.project.grosa.Domain.Model.Room

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class GroceryItemRoom(
    @PrimaryKey
    var id : String = "",
    val itemName : String = "bread",
    val itemImage : String = "url",
    val itemPrice : String = "R18",
    val itemShop : String = "Shopprite"
)
