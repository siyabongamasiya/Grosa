package Ecommerce.project.grosa.Presentation.Components

import Ecommerce.project.grosa.Domain.Model.Grocery
import Ecommerce.project.grosa.Domain.Model.GroceryItem
import Ecommerce.project.grosa.R
import Ecommerce.project.grosa.Utils.newGroceryItemDetailTypes
import Ecommerce.project.grosa.ui.theme.GrosaTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TopBar(isHome : Boolean,onBack : () -> Unit){
    when(isHome){
        true -> {
            Row (modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){

                Image(modifier = Modifier.size(30.dp),
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "logo")

                Icon(imageVector = Icons.Default.MoreVert,
                    tint = Color.White,
                    contentDescription = "menu")
            }
        }

        else -> {
            Box (modifier = Modifier
                .fillMaxWidth()
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
fun SearchBar(value : String,modifier: Modifier,onvaluechange : (newvalue : String) -> Unit){
    TextField(
        modifier = modifier
            .clip(RoundedCornerShape(16)),
        value = value,
        singleLine = true,
        onValueChange = {newText ->
                        onvaluechange.invoke(newText)
        }, placeholder = {
            Text(text = "Search Product",
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
fun Search(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        SearchBar(value = "", modifier = Modifier.weight(0.8f)) {

        }


        CustomButton(modifier = Modifier.weight(0.2f),
            icon = Icons.Default.Search,
            text = null) {



        }
    }
}

@Composable
fun GroceryItem(modifier: Modifier,item: GroceryItem){
    Column(modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        Image(modifier = Modifier
            .fillMaxWidth()
            .size(100.dp),
            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "item image")

        Column(modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)) {

            Text(modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                text = item.itemName)
            Text(modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                text = "Price - ${item.itemPrice}")
            Text(modifier = Modifier.fillMaxWidth(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                text = "Shop - ${item.itemShop}")

        }
    }
}

@Composable
fun FloatingActionButton(onclick: () -> Unit){
    Box (modifier = Modifier
        .clip(shape = CircleShape)
        .background(MaterialTheme.colorScheme.tertiary)
        .size(30.dp)){
        Icon(
            modifier = Modifier.align(Alignment.Center),
            tint = Color.White,
            imageVector = Icons.Default.Add,
            contentDescription = "add")
    }
}

@Composable
fun PopUp(names : List<String>,
          modifier: Modifier,
          onclick: (name : String) -> Unit){

    LazyColumn(modifier = modifier
        .background(MaterialTheme.colorScheme.secondary)
        .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        items(names, key = {
            it
        }){grocerylistname ->
            Text(text = grocerylistname,
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.titleMedium)
        }

    }

}

@Composable
fun Grocery(grocery: Grocery,modifier: Modifier){
    Column(modifier = modifier
        .clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colorScheme.secondary)
        .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = grocery.name,
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.titleMedium)

        Text(text = "R${grocery.totalPrice}",
            color = MaterialTheme.colorScheme.onSecondary,
            style = MaterialTheme.typography.labelMedium)
    }
}

@Composable
fun PopUpGroceries(modifier: Modifier,onsave : (newGrocery : Grocery) -> Unit){
    val newgrocery = rememberSaveable {
        mutableStateOf(Grocery())
    }
    Column(modifier = modifier
        .clip(RoundedCornerShape(10.dp))
        .background(MaterialTheme.colorScheme.primary)
        .fillMaxWidth()
        .padding(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally) {

        SearchBar(value = "", modifier = Modifier) {newGrocery ->
            newgrocery.value = Grocery(newGrocery)
        }

        CustomButton(modifier = Modifier, icon = null, text = "Save") {
            onsave.invoke(newgrocery.value)
        }
    }
}

@Composable
fun newGroceryItemDetail(text: String,
                         modifier: Modifier,
                         type : String,
                         onOpenImages : () -> Unit,
                         onchangeText : (newText : String) -> Unit){

    if (type.equals(newGroceryItemDetailTypes.Normal.name)){
        Column(modifier = Modifier
            .clip(RoundedCornerShape(10.dp))
            .fillMaxWidth()
            .border(width = 2.dp,
                color = MaterialTheme.colorScheme.tertiary)
            .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.Start) {
            Text(text = "text",
                color = MaterialTheme.colorScheme.onSecondary,
                style = MaterialTheme.typography.titleMedium)

            SearchBar(value = "", modifier = Modifier) {newvalue ->
                onchangeText.invoke(newvalue)
            }
        }
    }else{
        Box(modifier = Modifier
            .fillMaxWidth()
            .border(width = 2.dp,
                color = MaterialTheme.colorScheme.tertiary)
            .padding(10.dp)){

            CustomButton(modifier = Modifier,
                icon = null,
                text = "Choose Image") {
                onOpenImages.invoke()
            }

        }
    }

}

