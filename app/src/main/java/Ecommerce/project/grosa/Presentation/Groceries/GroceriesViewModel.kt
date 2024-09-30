package Ecommerce.project.grosa.Presentation.Groceries

import Ecommerce.project.grosa.Domain.Model.Room.GroceryRoom
import Ecommerce.project.grosa.Domain.Model.Room.GroceryWithItems
import Ecommerce.project.grosa.Domain.UseCases.SaveGrocery
import Ecommerce.project.grosa.Domain.UseCases.UseCasePack
import Ecommerce.project.grosa.Utils.EventBus
import Ecommerce.project.grosa.Utils.EventStates
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class GroceriesViewModel @Inject constructor(private val useCasePack: UseCasePack) : ViewModel() {
    private var _groceries = MutableStateFlow<List<GroceryWithItems>>(emptyList())
    val groceries = _groceries.onStart {
        getGroceries()
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun saveGroceries(groceryRoom: GroceryRoom){
        try {
            viewModelScope.launch {
                useCasePack.saveGrocery.invoke(groceryRoom)
                getGroceries()
            }
        }catch (exception : Exception){
            viewModelScope.launch {
                EventBus.sendEvent(EventStates.SOME_ERROR_OCCURED.name)
            }
        }
    }

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

    fun deleteGrocery(groceryRoom: GroceryRoom){
        try {
            viewModelScope.launch {
                useCasePack.deleteGrocery(groceryRoom)
                EventBus.sendEvent(EventStates.DONE.name)
            }
        }catch (exception : Exception){
            viewModelScope.launch {
                EventBus.sendEvent(EventStates.SOME_ERROR_OCCURED.name)
            }
        }
    }
}