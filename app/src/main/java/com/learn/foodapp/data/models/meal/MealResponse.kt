package com.learn.foodapp.data.models.meal

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class MealResponse(
    @SerializedName("meals")
    var meals: ArrayList<MealData>
)

@Parcelize
@Entity(tableName = "meals")
data class MealData(
    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @SerializedName("strMeal")
    var strMeal: String? = null,
    @SerializedName("strMealThumb")
    var strMealThumb: String? = null,
    @SerializedName("idMeal")
    var idMeal: String? = null
): Parcelable
