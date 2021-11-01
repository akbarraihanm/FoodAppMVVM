package com.learn.foodapp.data.models.category

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class CategoryResponse(
    @SerializedName("categories")
    var categories: ArrayList<CategoryData>
)

@Parcelize
data class CategoryData(
    @SerializedName("idCategory")
    var idCategory: String? = null,
    @SerializedName("strCategory")
    var strCategory: String? = null,
    @SerializedName("strCategoryThumb")
    var strCategoryThumb: String? = null,
    @SerializedName("strCategoryDescription")
    var strCategoryDescription: String? = null
): Parcelable
