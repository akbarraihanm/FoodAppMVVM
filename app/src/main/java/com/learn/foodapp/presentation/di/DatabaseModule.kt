package com.learn.foodapp.presentation.di

import android.app.Application
import androidx.room.Room
import com.learn.foodapp.data.db.MealDao
import com.learn.foodapp.data.db.MealDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideMealDatabase(app: Application): MealDatabase {
        return Room.databaseBuilder(app, MealDatabase::class.java, "meals_db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideMealDao(mealDatabase: MealDatabase): MealDao {
        return mealDatabase.getMealDao()
    }
}