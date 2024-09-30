package Ecommerce.project.grosa.Presentation.NewGroceryItem

import Ecommerce.project.grosa.Domain.Model.Room.GroceryItemRoom
import Ecommerce.project.grosa.Presentation.Components.CustomButton
import Ecommerce.project.grosa.Presentation.Components.TopBar
import Ecommerce.project.grosa.Presentation.Components.newGroceryItemDetail
import Ecommerce.project.grosa.Utils.TextBarHolders
import Ecommerce.project.grosa.Utils.TextbarKeyBoardTypes
import Ecommerce.project.grosa.Utils.newGroceryItemDetails
import Ecommerce.project.grosa.ui.theme.GrosaTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@Composable
fun DrawNewGroceryItemScreen(
    navigationController: NavHostController,
    newGroceryItemViewModel: NewGroceryItemViewModel,
    grocery: String
) {
    GrosaTheme {
        Scaffold(modifier = Modifier.fillMaxSize(),
            topBar = {
                TopBar(isHome = false){
                    navigationController.navigateUp()
                }
            }) {paddingValues ->
            Midsection(paddingValues = paddingValues,
                newGroceryItemViewModel = newGroceryItemViewModel,
                navigationController = navigationController,
                grocery = grocery)
        }
    }
}

@Composable
fun Midsection(paddingValues: PaddingValues,
               newGroceryItemViewModel: NewGroceryItemViewModel,
               navigationController: NavHostController,
               grocery: String){
    val newitemname = rememberSaveable {
        mutableStateOf("")
    }

    val newitemprice = rememberSaveable {
        mutableStateOf("")
    }

    val newitemshop = rememberSaveable {
        mutableStateOf("")
    }


    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.primary)
        .padding(top = paddingValues.calculateTopPadding(),
            start = 10.dp,
            end = 10.dp)) {

        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {

                newGroceryItemDetail(text = newGroceryItemDetails.ITEMNAME.value,
                    textbarvalue = newitemname.value,
                    keyboardtype = TextbarKeyBoardTypes.TEXT.name,
                    holder = TextBarHolders.ITEMNAME.value,
                    modifier = Modifier) {newText ->
                    newitemname.value = newText
                }

                newGroceryItemDetail(text = newGroceryItemDetails.ITEMPRICE.value,
                    textbarvalue = newitemprice.value,
                    keyboardtype = TextbarKeyBoardTypes.NUMBER.name,
                    holder = TextBarHolders.ITEMPRICE.value,
                    modifier = Modifier) {newText ->
                    newitemprice.value = newText
                }

                newGroceryItemDetail(text = newGroceryItemDetails.ITEMSHOP.value,
                    textbarvalue = newitemshop.value,
                    keyboardtype = TextbarKeyBoardTypes.TEXT.name,
                    holder = TextBarHolders.ITEMSHOP.value,
                    modifier = Modifier) {newText ->
                    newitemshop.value = newText
                }

            }

            CustomButton(modifier = Modifier.fillMaxWidth(), icon = null, text = "Save") {
                val newGrorceryItem = GroceryItemRoom(itemName = newitemname.value,
                    itemPrice = newitemprice.value,
                    itemShop = newitemshop.value)

                newGrorceryItem.id = "$grocery ${newitemname.value} ${newitemprice.value} ${newitemshop.value}"
                newGroceryItemViewModel.SaveGroceryItem(newGrorceryItem,grocery)
                navigationController.navigateUp()

            }

        }

    }

}