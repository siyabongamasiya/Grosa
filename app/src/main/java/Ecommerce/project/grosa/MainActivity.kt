package Ecommerce.project.grosa

import Ecommerce.project.grosa.Presentation.Groceries.DrawGroceries
import Ecommerce.project.grosa.Presentation.GroceryListItems.DrawGroceryListItems
import Ecommerce.project.grosa.Presentation.Home.DrawHome
import Ecommerce.project.grosa.Presentation.NewGroceryItem.DrawNewGroceryItemScreen
import Ecommerce.project.grosa.Presentation.Routes
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
        }
    }
}


@Composable
fun App(){
    val navigationController = rememberNavController()

    NavHost(navController = navigationController, startDestination = Routes.HomeRoute){
        composable<Routes.HomeRoute>{
            DrawHome(navigationController)
        }

        composable<Routes.NewGroceryRoute>{
            DrawNewGroceryItemScreen(navigationController)
        }

        composable<Routes.GroceriesRoute>{
            DrawGroceries(navigationController)
        }

        composable<Routes.GroceryItemsList>{
            DrawGroceryListItems(navigationController)
        }
    }
}