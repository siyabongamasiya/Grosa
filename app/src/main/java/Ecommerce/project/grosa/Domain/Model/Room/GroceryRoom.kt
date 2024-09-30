package Ecommerce.project.grosa.Domain.Model.Room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class GroceryRoom(
    @PrimaryKey
    val name : String = "next week",
    var totalPrice : Int = 0
)
