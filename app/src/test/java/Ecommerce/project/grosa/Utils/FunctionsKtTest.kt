package Ecommerce.project.grosa.Utils

import Ecommerce.project.grosa.Domain.Model.Room.GroceryItemRoom
import com.google.common.truth.Truth
import org.junit.Test

class FunctionsKtTest{
    @Test
    fun `make Test` (){
        //arrange
        val list = listOf(GroceryItemRoom(itemPrice = "5"), GroceryItemRoom(itemPrice = "5"))
        //act
        val result = calculateTotalPrice(list)

        //assert
        Truth.assertThat(result).isEqualTo(10)
    }
}