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

enum class EventStates(val value : String){
    NEUTRAL("neutral"),
    ITEM_NOT_FOUND("Item not found!!"),
    SOME_ERROR_OCCURED("Some thing went wrong,try again!!"),
    DONE("Done!!")
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

val GROCERIES = "Groceries"
val DATABASE = "Groceries"
val EMPTYLIST = "No Groceries added yet"
val EMPTYLIST2 = "No Grocery items added yet"
val ADDITEMS = "Add items"