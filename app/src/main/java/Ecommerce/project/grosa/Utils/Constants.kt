package Ecommerce.project.grosa.Utils


enum class newGroceryItemDetails(val value : String){
    ITEMNAME("Item name"),
    ITEMPRICE("Item Price"),
    ITEMSHOP("Item Shop")
}

enum class TextbarKeyBoardTypes{
    NUMBER,
    TEXT
}

enum class Tabs(val value : String){
    PRODUCTS("Products"),
    GROCERIES("Groceries")
}

enum class TextBarHolders(val value : String){
    SEARCHPRODUCT("Search product"),
    ITEMPRICE("Item Price"),
    ITEMNAME("Item name"),
    ITEMSHOP("Item Shop"),
    NEWGROCERY("Enter New Grocery Name")
}

val listofmenuchoices = listOf("Go to Groceries")