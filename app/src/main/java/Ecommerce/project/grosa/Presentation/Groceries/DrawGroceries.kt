package Ecommerce.project.grosa.Presentation.Groceries

import Ecommerce.project.grosa.Domain.Model.Room.GroceryRoom
import Ecommerce.project.grosa.Presentation.Components.TopBar
import Ecommerce.project.grosa.Presentation.Components.floatingActionButton
import Ecommerce.project.grosa.Presentation.Components.groceriesList
import Ecommerce.project.grosa.Presentation.Components.newGroceryDialogue
import Ecommerce.project.grosa.Presentation.Routes
import Ecommerce.project.grosa.Utils.EMPTYLIST
import Ecommerce.project.grosa.Utils.EventBus
import Ecommerce.project.grosa.Utils.GROCERIES
import Ecommerce.project.grosa.ui.theme.GrosaTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import kotlinx.coroutines.launch

@Composable
fun DrawGroceries(navigationController: NavController, groceriesViewModel: GroceriesViewModel) {
    GrosaTheme {
        val isdialogvisible = rememberSaveable {
            mutableStateOf(false)
        }

        val scope = rememberCoroutineScope()
        val snackbarstate = remember{
            SnackbarHostState()
        }

        LifecycleEventEffect(event = Lifecycle.Event.ON_START) {
            scope.launch {
                EventBus.event.collect{ state ->
                    if(state.isNotEmpty()) {
                        snackbarstate.showSnackbar(state)
                    }
                }
            }
        }

        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBar(isHome = true){
                }
            },snackbarHost = {
                SnackbarHost(hostState = snackbarstate)
            },
            floatingActionButton = {
                FloatingActionButton(containerColor = MaterialTheme.colorScheme.tertiary,
                    onClick = {
                        isdialogvisible.value = !isdialogvisible.value
                    }) {
                    floatingActionButton()
                }
            }) {paddingValues ->
            midSectionDrawGroceries(paddingValues = paddingValues,
                isdialoguevisble = isdialogvisible.value,
                navController = navigationController,
                groceriesViewModel = groceriesViewModel){
                isdialogvisible.value = false
            }
        }
    }
}

@Composable
fun midSectionDrawGroceries(paddingValues: PaddingValues,
                            isdialoguevisble : Boolean,
                            navController: NavController,
                            groceriesViewModel: GroceriesViewModel,
                            onhidedialogue : () -> Unit) {



    val groceries = groceriesViewModel.groceries.collectAsStateWithLifecycle()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .padding(
                top = paddingValues.calculateTopPadding() + 10.dp,
                start = 10.dp,
                end = 10.dp
            )
    ) {
        if (groceries.value.isNotEmpty()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    modifier = Modifier
                        .clip(RoundedCornerShape(30.dp))
                        .background(Color.DarkGray)
                        .padding(10.dp),
                    text = GROCERIES,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )

                groceriesList(
                    list = groceries.value,
                    modifier = Modifier.fillMaxSize(),
                    ondeleteGroceryItem = {grocery ->
                        groceriesViewModel.deleteGrocery(grocery)
                    }
                ) { grocery ->
                    navController.navigate(Routes.GroceryItemsList(grocery.name))
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
                    text = EMPTYLIST,
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )

            }
        }

        if (isdialoguevisble) {
            newGroceryDialogue(
                modifier = Modifier.align(Alignment.Center),
                onsave = { grocery ->
                    groceriesViewModel.saveGroceries(grocery)
                }) {
                onhidedialogue.invoke()
            }
        }
    }

}