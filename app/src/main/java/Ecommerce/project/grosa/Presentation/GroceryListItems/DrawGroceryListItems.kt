package Ecommerce.project.grosa.Presentation.GroceryListItems

import Ecommerce.project.grosa.Domain.Model.Grocery
import Ecommerce.project.grosa.Domain.Model.GroceryItem
import Ecommerce.project.grosa.Presentation.Components.GroceryOptions
import Ecommerce.project.grosa.Presentation.Components.TopBar
import Ecommerce.project.grosa.Presentation.Components.floatingActionButton
import Ecommerce.project.grosa.Presentation.Components.groceryItemList
import Ecommerce.project.grosa.Presentation.Routes
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController


@Composable
fun DrawGroceryListItems(navigationController: NavHostController) {
    GrosaTheme {
        var isVisibleOptions = rememberSaveable {
            mutableStateOf(false)
        }

        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBar(isHome = false) {
                    navigationController.navigateUp()
                }
            },
            floatingActionButton = {
                FloatingActionButton(containerColor = MaterialTheme.colorScheme.tertiary,
                    onClick = {
                        navigationController.navigate(Routes.NewGroceryRoute())
                    }) {
                    floatingActionButton()
                }
            }) {paddingValues ->
            val grocery = Grocery()
            misSectionDrawGroceryListItems(paddingValues = paddingValues,
                isVisibleOptions = isVisibleOptions.value,
                navigationController,grocery){

                isVisibleOptions.value = !isVisibleOptions.value

            }

        }
    }
}


@Composable
fun misSectionDrawGroceryListItems(paddingValues: PaddingValues,
                                   isVisibleOptions : Boolean,
                                   navController : NavController,
                                   grocery: Grocery,
                                   onshowpopup : () -> Unit){

    val groceryItems = listOf(
        GroceryItem("ginger", itemPrice = "R20", itemShop = "shoprite"),
        GroceryItem("maas", itemPrice = "R76", itemShop = "checkers"),
        GroceryItem("mpuphu", itemPrice = "R180", itemShop = "pick n pay")
    )

    val groceries = rememberSaveable {
        mutableStateOf(listOf(
            Grocery("Ekhaya").name,
            Grocery("Next month").name,
            Grocery("next week").name))
    }

    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary)
        .padding(
            top = paddingValues.calculateTopPadding(),
            start = 10.dp,
            end = 10.dp
        )) {

        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Text(text = "${grocery.name} items",
                color = MaterialTheme.colorScheme.onPrimary,
                style = MaterialTheme.typography.titleLarge)


            groceryItemList(list = groceryItems,
                isProducts = false,
                modifier = Modifier.fillMaxSize(),
                ondeleteGroceryItem = {

                }){
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