package Ecommerce.project.grosa.Domain.Model.Room

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation



data class GroceryWithItems(

    @Embedded
    val grocery : GroceryRoom = GroceryRoom(),
    @Relation(
        parentColumn = "name",
        entityColumn = "id",
        associateBy = Junction(groceryAndItemCross::class)
    )
    val groceryitems : List<GroceryItemRoom> = emptyList()
)
