package com.fahimshahrierrasel.moviedb.data.model


import com.google.gson.annotations.SerializedName

data class CreditResponse(
    @SerializedName("cast")
    val cast: List<CreditCast>,
    @SerializedName("id")
    val id: Int
)

data class CreditCast(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("profile_path")
    val profilePath: String
)