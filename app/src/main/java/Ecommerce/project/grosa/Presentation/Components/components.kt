package Ecommerce.project.grosa.Presentation.Components

import Ecommerce.project.grosa.Domain.Model.Room.GroceryRoom
import Ecommerce.project.grosa.Domain.Model.Room.GroceryItemRoom
import Ecommerce.project.grosa.Domain.Model.Room.GroceryWithItems
import Ecommerce.project.grosa.R
import Ecommerce.project.grosa.Utils.TextBarHolders
import Ecommerce.project.grosa.Utils.TextbarKeyBoardTypes
import Ecommerce.project.grosa.Utils.calculateTotalPrice
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Save
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties


@Composable
fun TopBar(isHome : Boolean,onBack : () -> Unit = {}){
    when(isHome){
        true -> {
            Box (modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(10.dp)){
                Image(modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo")
            }
        }


        false -> {
            Box (modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.tertiary)
                .padding(10.dp)){
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable {
                            onBack.invoke()
                        }
                        .size(30.dp),
                    imageVector = Icons.Default.ArrowBack,
                    tint = Color.White,
                    contentDescription = "back")
            }
        }
    }
}

@Composable
fun TextBar(value : String,
            holder: String
            ,modifier: Modifier,
            keyboardtype : String,
            onvaluechange : (newvalue : String) -> Unit){

    OutlinedTextField(
        modifier = modifier
            ,
        value = value,
        keyboardOptions = KeyboardOptions.Default.copy(showKeyboardOnFocus = true,
            keyboardType = if (keyboardtype.equals(TextbarKeyBoardTypes.NUMBER.name))
                KeyboardType.Number
            else KeyboardType.Text),

        singleLine = true,
        onValueChange = {newText ->
                        onvaluechange.invoke(newText)
        }, placeholder = {
            Text(text = holder,
                color = Color.LightGray,
                style = MaterialTheme.typography.labelMedium
            )
        },
        colors = TextFieldDefaults.colors(
            cursorColor = MaterialTheme.colorScheme.tertiary,
            focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
            unfocusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
            unfocusedTextColor = Color.Black,
            focusedTextColor = Color.Black,
            focusedContainerColor = Color.White,
            unfocusedContainerColor = Color.White
        )
    )
}

@Composable
fun CustomButton(modifier: Modifier,icon : ImageVector?,text : String?,onclick : () -> Unit){
    ElevatedButton(modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.tertiary
        ),
        onClick = { onclick.invoke() }) {
        if(text != null) {
            Text(
                text = text,
                color = MaterialTheme.colorScheme.onTertiary,
                style = MaterialTheme.typography.labelMedium
            )
        }

        if (icon != null){
            Icon(imageVector = icon,
                tint = Color.White,
                contentDescription = "search icon")
        }
    }
}


@Composable
fun groceryItemList(list: List<GroceryItemRoom>,
                    modifier: Modifier,
                    isProducts : Boolean,
                    ondeleteGroceryItem: (groceryItem : GroceryItemRoom) -> Unit = {},
                    onsaveGroceryItem: (groceryItem : GroceryItemRoom) -> Unit = {}
                    ){

    LazyColumn(modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        items(list){groceryItem ->
            groceryItem (
                modifier = Modifier,
                isProducts = isProducts,
                item = groceryItem,
                ondeleteGroceryItem = {
                    ondeleteGroceryItem.invoke(groceryItem)
                }
            ){
                onsaveGroceryItem.invoke(groceryItem)
            }
        }

    }
}

@Composable
fun groceriesList(list: List<GroceryWithItems>,
                  modifier: Modifier,
                  ondeleteGroceryItem: (grocery : GroceryRoom) -> Unit,
                  onItemClick: (groceryRoom: GroceryRoom) -> Unit){
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        items(list){grocerywithitems ->
            val totalprice = calculateTotalPrice(grocerywithitems.groceryitems)
            grocerywithitems.grocery.totalPrice = totalprice

            grocery(groceryRoom = grocerywithitems.grocery,
                modifier = Modifier.fillMaxWidth(),
                ondeleteGroceryItem = {
                    ondeleteGroceryItem.invoke(grocerywithitems.grocery)
                }){
                onItemClick.invoke(grocerywithitems.grocery)
            }
        }

    }
}

@Composable
fun Search(value: String,onChangeValue : (newValue : String) -> Unit){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        TextBar(value = value,
            holder = TextBarHolders.SEARCHPRODUCT.value,
            keyboardtype = TextbarKeyBoardTypes.TEXT.name,
            modifier = Modifier.weight(0.8f)) {newValue ->
            onChangeValue.invoke(newValue)
        }


        CustomButton(modifier = Modifier.weight(0.2f),
            icon = Icons.Default.Search,
            text = null) {



        }
    }
}

@Composable
fun groceryItem(modifier: Modifier,
                item: GroceryItemRoom,
                isProducts: Boolean,
                ondeleteGroceryItem : () -> Unit,
                onsaveGroceryItem : () -> Unit){

    Column(modifier = modifier
        .fillMaxWidth()
        .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 2.dp,
                color = Color.Black
            )
            .size(200.dp),
            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "item image")

        Row(modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically){

            Column(modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .padding(10.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.spacedBy(10.dp)) {

                Text(
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSecondary,
                    text = item.itemName)
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondary,
                    text = "Price - ${item.itemPrice}")
                Text(
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.onSecondary,
                    text = "Shop - ${item.itemShop}")

            }

            Column (modifier = Modifier
                .background(MaterialTheme.colorScheme.secondary)
                .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally){
                if (!isProducts) {
                    Icon(
                        modifier = Modifier
                            .size(30.dp)
                            .clickable {
                                ondeleteGroceryItem.invoke()
                            },
                        tint = MaterialTheme.colorScheme.onSecondary,
                        imageVector = Icons.Default.Delete,
                        contentDescription = "delete"
                    )
                }

                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            onsaveGroceryItem.invoke()
                        },
                    tint = MaterialTheme.colorScheme.onSecondary,
                    imageVector = Icons.Default.Save,
                    contentDescription = "save")
            }


        }

    }
}

@Composable
fun floatingActionButton(){
    Icon(
        tint = Color.White,
        imageVector = Icons.Default.Add,
        contentDescription = "add")
}



@Composable
fun grocery(groceryRoom: GroceryRoom,
            modifier: Modifier,
            ondeleteGroceryItem: () -> Unit,
            onItemClick: () -> Unit){
    Row (
        modifier = modifier
        .clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colorScheme.secondary)
        .fillMaxWidth()
        .padding(10.dp)
        .clickable {
            onItemClick.invoke()
        }, horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically){

        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.Start) {
            Text(
                modifier = Modifier,
                text = groceryRoom.name,
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.titleMedium)

            Text(
                modifier = Modifier,
                text = "R${groceryRoom.totalPrice}",
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.labelMedium)
        }

        Icon(
            modifier = Modifier.clickable {
                ondeleteGroceryItem.invoke()
            },
            imageVector = Icons.Default.Delete,
            tint = Color.White,
            contentDescription = "delete grocery")

    }

}



@Composable
fun newGroceryItemDetail(text: String,
                         textbarvalue : String,
                         keyboardtype: String,
                         modifier: Modifier,
                         holder : String,
                         onchangeText : (newText : String) -> Unit = {}){

    Column(modifier = modifier
        .clip(RoundedCornerShape(10.dp))
        .fillMaxWidth()
        .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.Start) {

        Text(text = text,
            color = MaterialTheme.colorScheme.onPrimary,
            style = MaterialTheme.typography.titleMedium)

        TextBar(value = textbarvalue,
            keyboardtype = if (keyboardtype == TextbarKeyBoardTypes.NUMBER.name) TextbarKeyBoardTypes.NUMBER.name
            else TextbarKeyBoardTypes.TEXT.name,
            holder = holder,
            modifier = Modifier) {newvalue ->
            onchangeText.invoke(newvalue)
        }
    }

}

@Composable
fun CustomTab(tabno : Int =0,currentPage : Int = 0,text: String,onScroll : () -> Unit){
    val pageNo = rememberSaveable {
        mutableStateOf(tabno)
    }

    Tab(
        selected = currentPage == pageNo.value,
        modifier = Modifier.padding(5.dp),
        onClick = {
            onScroll.invoke()
        },
    ) {
        val bgcolor : Color
        if (currentPage == pageNo.value){
            bgcolor = MaterialTheme.colorScheme.tertiary
        }else{
            bgcolor = Color.DarkGray
        }

        Text(text = text,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier
                .background(
                    bgcolor,
                    RoundedCornerShape(16.dp)
                )
                .padding(10.dp))
    }
}


@Composable
fun newGroceryDialogue(modifier: Modifier,
                       onsave : (newGroceryRoom : GroceryRoom) -> Unit,
                       onDismiss: () -> Unit){
    val newgrocery = remember {
        mutableStateOf(GroceryRoom())
    }


    Box(modifier = modifier) {
        Popup(
            alignment = Alignment.Center,
            properties = PopupProperties(
                focusable = true
            ),
            onDismissRequest = {
                onDismiss.invoke()
            }) {

            Column(modifier = modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Yellow)
                .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally) {

                TextBar(value = newgrocery.value.name,
                    holder = TextBarHolders.NEWGROCERY.value,
                    keyboardtype = TextbarKeyBoardTypes.TEXT.name,
                    modifier = Modifier) {newGrocery ->
                    newgrocery.value = GroceryRoom(newGrocery)
                }

                CustomButton(modifier = Modifier, icon = null, text = "Save") {
                    onsave.invoke(newgrocery.value)
                    onDismiss.invoke()
                }
            }

        }
    }


}
@Composable
fun GroceryOptions(names : List<String>,
                   modifier: Modifier,
                   color : Color,
                   onclick: (grocery : String) -> Unit,
                   onDismiss : () -> Unit){

    Box(modifier = modifier) {
        Popup(
            alignment = Alignment.Center,
            properties = PopupProperties(
                focusable = true
            ),
            onDismissRequest = {
                onDismiss.invoke()
            }) {

            LazyColumn(modifier = modifier
                .clip(RoundedCornerShape(16.dp))
                .background(color)
                .padding(10.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,) {

                items(names, key = {
                    it
                }){grocerylistname ->
                    Text(
                        modifier = Modifier.clickable {
                            onclick.invoke(grocerylistname)
                            onDismiss.invoke()
                        },
                        text = grocerylistname,
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.titleMedium)
                }

            }

        }
    }


}

