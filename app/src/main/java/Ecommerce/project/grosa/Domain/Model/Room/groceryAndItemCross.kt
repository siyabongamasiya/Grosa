package Ecommerce.project.grosa.Domain.Model.Room

import androidx.room.Entity

@Entity(primaryKeys = ["name","id"])
data class groceryAndItemCross(
    val name : String,
    val id : String
)
