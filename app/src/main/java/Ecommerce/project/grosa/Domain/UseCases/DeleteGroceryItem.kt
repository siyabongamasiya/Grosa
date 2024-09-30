package Ecommerce.project.grosa.Domain.UseCases

import Ecommerce.project.grosa.Data.Repository.RepositoryImpl
import Ecommerce.project.grosa.Domain.Model.Room.GroceryItemRoom
import javax.inject.Inject

class DeleteGroceryItem @Inject constructor(val repositoryImpl: RepositoryImpl) {

    suspend operator fun invoke(groceryItemRoom: GroceryItemRoom){
        repositoryImpl.deleteGroceryItem(groceryItemRoom)
    }
}