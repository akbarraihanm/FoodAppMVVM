package com.learn.foodapp.data.models.detailmeal

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class DetailMealResponse(
    @SerializedName("meals")
    var meals: ArrayList<DetailMealData>
)

@Parcelize
data class DetailMealData(
    @SerializedName("idMeal")
    var idMeal: String? = null,
    @SerializedName("strMeal")
    var strMeal: String? = null,
    @SerializedName("strArea")
    var strArea: String? = null,
    @SerializedName("strInstructions")
    var strInstructions: String? = null,
    @SerializedName("strMealThumb")
    var strMealThumb: String? = null,
    @SerializedName("strYoutube")
    var strYoutube: String? = null,
    @SerializedName("strSource")
    var strSource: String? = null
): Parcelable
