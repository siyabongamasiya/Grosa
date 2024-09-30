package Ecommerce.project.grosa.Domain.UseCases

import javax.inject.Inject

data class UseCasePack @Inject constructor(
    val getGrocery: GetGrocery,
    val saveGrocery: SaveGrocery,
    val getGroceryByName: GetGroceryByName,
    val saveGroceryItem: SaveGroceryItem,
    val saveGroceryAndItemCross: SaveGroceryAndItemCross,
    val deleteGrocery: DeleteGrocery,
    val deleteGroceryItem: DeleteGroceryItem,
    val deleteGroceryAndItemCross: DeleteGroceryAndItemCross
)