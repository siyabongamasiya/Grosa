package Ecommerce.project.grosa.Domain.UseCases

import Ecommerce.project.grosa.Data.Repository.RepositoryImpl
import Ecommerce.project.grosa.Domain.Model.Room.GroceryRoom
import javax.inject.Inject

class DeleteGrocery @Inject constructor(val repositoryImpl: RepositoryImpl) {

    suspend operator fun invoke(groceryRoom: GroceryRoom){
        repositoryImpl.deleteGrocery(groceryRoom)
    }
}