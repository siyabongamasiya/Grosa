package Ecommerce.project.grosa

import Ecommerce.project.grosa.Presentation.Groceries.DrawGroceries
import Ecommerce.project.grosa.Presentation.Groceries.GroceriesViewModel
import Ecommerce.project.grosa.Presentation.GroceryListItems.DrawGroceryListItems
import Ecommerce.project.grosa.Presentation.GroceryListItems.GroceriesListItemsViewModel
import Ecommerce.project.grosa.Presentation.NewGroceryItem.DrawNewGroceryItemScreen
import Ecommerce.project.grosa.Presentation.NewGroceryItem.NewGroceryItemViewModel
import Ecommerce.project.grosa.Presentation.Routes
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navigationController = rememberNavController()
            App(navigationController)
        }
    }
}


@Composable
fun App(navigationController : NavHostController){
    val groceriesViewModel = hiltViewModel<GroceriesViewModel>()
    val groceriesListItemsViewModel = hiltViewModel<GroceriesListItemsViewModel>()
    val newGroceryItemViewModel = hiltViewModel<NewGroceryItemViewModel>()

    NavHost(navController = navigationController, startDestination = Routes.GroceriesRoute){

        composable<Routes.GroceriesRoute>{
            DrawGroceries(navigationController,groceriesViewModel)
        }

        composable<Routes.NewGroceryRoute>{
            val args = it.toRoute<Routes.NewGroceryRoute>()
            val grocery = args.grocery

            DrawNewGroceryItemScreen(navigationController,newGroceryItemViewModel,grocery)
        }

        composable<Routes.GroceryItemsList>{
            val args = it.toRoute<Routes.GroceryItemsList>()

            DrawGroceryListItems(navigationController,groceriesListItemsViewModel,args.grocery)
        }
    }
}