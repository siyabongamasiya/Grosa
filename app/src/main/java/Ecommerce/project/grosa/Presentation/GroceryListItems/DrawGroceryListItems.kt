package Ecommerce.project.grosa.Presentation.GroceryListItems

import Ecommerce.project.grosa.Presentation.Components.GroceryOptions
import Ecommerce.project.grosa.Presentation.Components.TopBar
import Ecommerce.project.grosa.Presentation.Components.floatingActionButton
import Ecommerce.project.grosa.Presentation.Components.groceryItemList
import Ecommerce.project.grosa.Presentation.Routes
import Ecommerce.project.grosa.Utils.ADDITEMS
import Ecommerce.project.grosa.Utils.EMPTYLIST
import Ecommerce.project.grosa.Utils.EMPTYLIST2
import Ecommerce.project.grosa.Utils.EventBus
import Ecommerce.project.grosa.Utils.EventStates
import Ecommerce.project.grosa.Utils.calculateTotalPrice
import Ecommerce.project.grosa.ui.theme.GrosaTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LifecycleEventEffect
import androidx.lifecycle.compose.LifecycleStartEffect
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import kotlinx.coroutines.launch


@Composable
fun DrawGroceryListItems(
    navigationController: NavHostController,
    groceriesListItemsViewModel: GroceriesListItemsViewModel,
    grocery: String
) {
    GrosaTheme {
        groceriesListItemsViewModel.setGrocery(grocery)
        var isVisibleOptions = rememberSaveable {
            mutableStateOf(false)
        }

        val scope = rememberCoroutineScope()
        val snackbarstate = remember{
            SnackbarHostState()
        }

        LifecycleEventEffect(event = Lifecycle.Event.ON_START) {
            scope.launch {
                EventBus.event.collect{state ->
                    if(state.isNotEmpty()) {
                        snackbarstate.showSnackbar(state)
                    }
                }
            }
        }


        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBar(isHome = false) {
                    navigationController.navigateUp()
                }
            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarstate)
            },
            floatingActionButton = {
                FloatingActionButton(
                    modifier = Modifier.padding(bottom = 50.dp),
                    containerColor = MaterialTheme.colorScheme.tertiary,
                    onClick = {
                        navigationController.navigate(Routes.NewGroceryRoute(grocery))
                    }) {
                    floatingActionButton()
                }
            }) {paddingValues ->
            misSectionDrawGroceryListItems(
                paddingValues = paddingValues,
                isVisibleOptions = isVisibleOptions.value,
                navController = navigationController,
                groceriesListItemsViewModel = groceriesListItemsViewModel,
                groceryName = grocery){

                isVisibleOptions.value = !isVisibleOptions.value

            }

        }
    }
}


@Composable
fun misSectionDrawGroceryListItems(paddingValues: PaddingValues,
                                   isVisibleOptions : Boolean,
                                   navController : NavController,
                                   groceriesListItemsViewModel: GroceriesListItemsViewModel,
                                   groceryName : String,
                                   onshoworhidepopup : () -> Unit){

    val grocerieswithitems = groceriesListItemsViewModel.groceries.collectAsStateWithLifecycle()

    val grocerywithitem = grocerieswithitems.value.find {grocerywithitem ->
        grocerywithitem.grocery.name == groceryName
    }

    if(grocerywithitem != null) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary)
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    start = 10.dp,
                    end = 10.dp
                )
        ) {

            if(grocerywithitem.groceryitems.isNotEmpty()) {
                val totalprice = calculateTotalPrice(grocerywithitem.groceryitems)

                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "${grocerywithitem.grocery.name} items",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = "Total price = R$totalprice",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelMedium
                    )

                    Divider(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(2.dp),
                        color = Color.Black)


                    groceryItemList(list = grocerywithitem.groceryitems,
                        isProducts = false,
                        modifier = Modifier.fillMaxSize(),
                        ondeleteGroceryItem = {groceryItem ->
                            groceriesListItemsViewModel.deleteGroceryItem(groceryItem)
                        }) {groceryItem ->
                        groceriesListItemsViewModel.setCurrentOptionedGroceryItem(groceryItem)
                        onshoworhidepopup.invoke()
                    }
                }
            }else{
                Box (modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
                    .padding(
                        top = paddingValues.calculateTopPadding() + 10.dp,
                        start = 10.dp,
                        end = 10.dp
                    )){

                    Text(
                        modifier = Modifier
                            .clip(RoundedCornerShape(30.dp))
                            .background(Color.DarkGray)
                            .align(Alignment.Center)
                            .padding(10.dp),
                        text = ADDITEMS,
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White
                    )

                }
            }

            if (isVisibleOptions) {
                val groceryStringList = grocerieswithitems.value.map { grocerywithitem ->
                    grocerywithitem.grocery.name
                }
                GroceryOptions(
                    names = groceryStringList,
                    modifier = Modifier.align(Alignment.Center),
                    color = Color.Yellow,
                    onclick = {grocery ->
                        groceriesListItemsViewModel.SaveGroceryItemToAnotherGrocery(grocery)
                    }
                ) {
                    onshoworhidepopup.invoke()
                }
            }
        }
    }
}