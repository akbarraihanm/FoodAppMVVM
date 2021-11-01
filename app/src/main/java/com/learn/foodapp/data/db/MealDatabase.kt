package com.learn.foodapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.learn.foodapp.data.models.meal.MealData

@Database(
    entities = [MealData::class],
    version = 1,
    exportSchema = false
)
abstract class MealDatabase: RoomDatabase() {
    abstract fun getMealDao(): MealDao
}