package Ecommerce.project.grosa.Presentation.GroceryListItems

import Ecommerce.project.grosa.Domain.Model.Room.GroceryItemRoom
import Ecommerce.project.grosa.Domain.Model.Room.GroceryRoom
import Ecommerce.project.grosa.Domain.Model.Room.GroceryWithItems
import Ecommerce.project.grosa.Domain.Model.Room.groceryAndItemCross
import Ecommerce.project.grosa.Domain.UseCases.UseCasePack
import Ecommerce.project.grosa.Utils.EventBus
import Ecommerce.project.grosa.Utils.EventStates
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GroceriesListItemsViewModel @Inject constructor(private val useCasePack: UseCasePack) : ViewModel() {
    private var _groceries = MutableStateFlow<List<GroceryWithItems>>(emptyList())

    private var grocery : String =""

    private var currentOptionedGroceryItem : GroceryItemRoom = GroceryItemRoom()

    val groceries = _groceries.onStart {
        getGroceries()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())



    fun getGroceries(){
        try {
            viewModelScope.launch {
                useCasePack.getGrocery().collect { newgrocerylist ->
                    _groceries.value = newgrocerylist
                }
            }
        }catch (exception : Exception){
            viewModelScope.launch {
                EventBus.sendEvent(EventStates.SOME_ERROR_OCCURED.name)
            }
        }
    }

    fun deleteGroceryItem(groceryItemRoom: GroceryItemRoom){
        try {
            viewModelScope.launch {
                useCasePack.deleteGroceryItem(groceryItemRoom)
                useCasePack.deleteGroceryAndItemCross(
                    groceryAndItemCross(
                        grocery,
                        groceryItemRoom.id
                    )
                )
                getGroceries()
                EventBus.sendEvent(EventStates.DONE.name)
            }
        }catch (exception : Exception){
            viewModelScope.launch {
                EventBus.sendEvent(EventStates.SOME_ERROR_OCCURED.name)
            }
        }
    }

    fun SaveGroceryItemToAnotherGrocery(groceryName : String){
        try {
            viewModelScope.launch {
                //create new id and groceryitem for new cross refrence so that
                // deleting a grocery item does not delete for all groceries
                val newId = "$groceryName ${currentOptionedGroceryItem.itemName} ${currentOptionedGroceryItem.itemPrice} ${currentOptionedGroceryItem.itemShop}"
                val newGroceryItem = GroceryItemRoom(id = newId, itemName = currentOptionedGroceryItem.itemName, itemPrice = currentOptionedGroceryItem.itemPrice, itemShop = currentOptionedGroceryItem.itemShop)

                val crossItem = groceryAndItemCross(groceryName, newId)
                useCasePack.saveGroceryItem(newGroceryItem)
                useCasePack.saveGroceryAndItemCross.invoke(crossItem)
                EventBus.sendEvent(EventStates.DONE.name)
            }
        }catch (exception : Exception){
            viewModelScope.launch {
                EventBus.sendEvent(EventStates.SOME_ERROR_OCCURED.name)
            }
        }
    }

    fun setGrocery(newgrocery : String){
        this.grocery = newgrocery
    }

    fun setCurrentOptionedGroceryItem(groceryItemRoom: GroceryItemRoom){
        this.currentOptionedGroceryItem = groceryItemRoom
    }
}