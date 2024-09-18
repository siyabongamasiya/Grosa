package Ecommerce.project.grosa.Presentation.Groceries

import Ecommerce.project.grosa.Domain.Model.Grocery
import Ecommerce.project.grosa.Presentation.Components.floatingActionButton
import Ecommerce.project.grosa.Presentation.Components.groceriesList
import Ecommerce.project.grosa.Presentation.Components.newGroceryDialogue
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
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DrawGroceries(navigationController: NavController) {
    GrosaTheme {
        val isdialogvisible = rememberSaveable {
            mutableStateOf(false)
        }

        Scaffold(modifier = Modifier.fillMaxSize(),
            floatingActionButton = {
                FloatingActionButton(containerColor = MaterialTheme.colorScheme.tertiary,
                    onClick = {
                        isdialogvisible.value = !isdialogvisible.value
                    }) {
                    floatingActionButton()
                }
            }) {paddingValues ->
            misSectionDrawGroceries(paddingValues = paddingValues,
                isdialoguevisble = isdialogvisible.value,
                navController = navigationController){
                isdialogvisible.value = false
            }
        }
    }
}

@Composable
fun misSectionDrawGroceries(paddingValues: PaddingValues,
                            isdialoguevisble : Boolean,
                            navController: NavController,
                            onhidedialogue : () -> Unit){

    val groceries = remember{
        mutableStateOf(listOf(Grocery("next week"),
            Grocery("next month"),
            Grocery("ekhaya")))
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

            groceriesList(list = groceries.value, modifier = Modifier.fillMaxSize()){grocery ->
                navController.navigate(Routes.GroceryItemsList(grocery.name))
            }

        }

        if (isdialoguevisble){
            newGroceryDialogue(modifier = Modifier.align(Alignment.Center), onsave = {grocery ->

            }) {
                onhidedialogue.invoke()
            }
        }
    }
}