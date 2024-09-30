package Ecommerce.project.grosa.Domain.UseCases

import Ecommerce.project.grosa.Data.Repository.RepositoryImpl
import Ecommerce.project.grosa.Domain.Model.Room.groceryAndItemCross
import javax.inject.Inject

class DeleteGroceryAndItemCross @Inject constructor(val repositoryImpl: RepositoryImpl) {

    suspend operator fun invoke(groceryAndItemCross: groceryAndItemCross){
        repositoryImpl.deleteGroceryAndItemCross(groceryAndItemCross)
    }
}