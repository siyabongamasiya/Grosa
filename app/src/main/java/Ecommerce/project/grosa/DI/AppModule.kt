package Ecommerce.project.grosa.DI

import Ecommerce.project.grosa.Data.Room.GroceriesDAO
import Ecommerce.project.grosa.Data.Room.GroceriesDatabase
import Ecommerce.project.grosa.Utils.GROCERIES
import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun ProvidesDao(@ApplicationContext context: Context) : GroceriesDAO{
        return Room.
        databaseBuilder(context,GroceriesDatabase::class.java, GROCERIES)
            .fallbackToDestructiveMigration()
            .build().getGroceriesDao()
    }
}