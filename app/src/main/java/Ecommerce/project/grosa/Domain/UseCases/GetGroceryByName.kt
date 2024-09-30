package Ecommerce.project.grosa.Domain.UseCases

import Ecommerce.project.grosa.Data.Repository.RepositoryImpl
import Ecommerce.project.grosa.Domain.Model.Room.GroceryRoom
import javax.inject.Inject

class GetGroceryByName @Inject constructor(private val repositoryImpl: RepositoryImpl) {
    operator fun invoke(groceryName : String) : GroceryRoom?{
        return repositoryImpl.getGroceryByName(groceryName)
    }
}