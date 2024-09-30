package Ecommerce.project.grosa.Domain.UseCases

import Ecommerce.project.grosa.Data.Repository.RepositoryImpl
import Ecommerce.project.grosa.Domain.Model.Room.GroceryRoom
import Ecommerce.project.grosa.Domain.Model.Room.GroceryWithItems
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetGrocery @Inject constructor(private val repositoryImpl: RepositoryImpl) {
    suspend operator fun invoke() : Flow<List<GroceryWithItems>>{
        return repositoryImpl.getGroceries()
    }
}