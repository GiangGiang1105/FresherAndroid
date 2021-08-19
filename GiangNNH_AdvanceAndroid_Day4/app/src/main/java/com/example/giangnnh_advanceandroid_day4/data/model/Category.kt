package com.example.giangnnh_advanceandroid_day4.data.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Category(
    @SerializedName("idCategory") var idCategory: String,
    @SerializedName("strCategory") var strCategory: String,
    @SerializedName("strCategoryThumb") var strCategoryThumb: String,
    @SerializedName("strCategoryDescription") var strCategoryDescription: String
) : Serializable