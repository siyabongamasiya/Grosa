package Ecommerce.project.grosa.Presentation.NewGroceryItem

import Ecommerce.project.grosa.Domain.Model.Room.GroceryItemRoom
import Ecommerce.project.grosa.Domain.Model.Room.groceryAndItemCross
import Ecommerce.project.grosa.Domain.UseCases.UseCasePack
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewGroceryItemViewModel @Inject constructor(val useCasePack: UseCasePack) : ViewModel() {


    fun SaveGroceryItem(groceryItemRoom: GroceryItemRoom, groceryName : String){
        viewModelScope.launch {
            val crossItem = groceryAndItemCross(groceryName,groceryItemRoom.id)
            useCasePack.saveGroceryItem.invoke(groceryItemRoom)
            useCasePack.saveGroceryAndItemCross.invoke(crossItem)
        }
    }
}