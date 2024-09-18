package Ecommerce.project.grosa.Presentation.Products

import Ecommerce.project.grosa.Domain.Model.Grocery
import Ecommerce.project.grosa.Domain.Model.GroceryItem
import Ecommerce.project.grosa.Presentation.Components.GroceryOptions
import Ecommerce.project.grosa.Presentation.Components.Search
import Ecommerce.project.grosa.Presentation.Components.groceryItemList
import Ecommerce.project.grosa.ui.theme.GrosaTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController


@Composable
fun DrawProducts(navigationController: NavController) {


    GrosaTheme {
        var isVisibleOptions = rememberSaveable {
            mutableStateOf(false)
        }

        Scaffold(
            modifier = Modifier.fillMaxSize()) {paddingValues ->

            MidSection(paddingValues = paddingValues,
                isVisibleOptions = isVisibleOptions.value,
                navigationController = navigationController){

                isVisibleOptions.value = !isVisibleOptions.value

            }

        }
    }
}

@Composable
fun MidSection(paddingValues: PaddingValues,
               isVisibleOptions : Boolean,
               navigationController: NavController,
               onshowpopup : () -> Unit){

    val search = rememberSaveable {
        mutableStateOf("")
    }

    val groceries = rememberSaveable {
        mutableStateOf(listOf(
            Grocery("Ekhaya").name,
            Grocery("Next month").name,
            Grocery("next week").name))
    }

    val groceryItems = listOf(GroceryItem("ginger", itemPrice = "R20", itemShop = "shoprite"),
        GroceryItem("maas", itemPrice = "R76", itemShop = "checkers"),
        GroceryItem("mpuphu", itemPrice = "R180", itemShop = "pick n pay"))

    Box(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .fillMaxSize()
            .padding(top = paddingValues.calculateTopPadding()),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Search(search.value){newvalue ->
                search.value = newvalue
            }
            groceryItemList(list = groceryItems,
                isProducts = true,
                modifier = Modifier){

                onshowpopup.invoke()
            }
        }

        if(isVisibleOptions) {
            GroceryOptions(
                names = groceries.value,
                modifier = Modifier.align(Alignment.Center),
                color = Color.Yellow,
                onclick = {

                }
            ) {
                onshowpopup.invoke()
            }
        }
    }
}

