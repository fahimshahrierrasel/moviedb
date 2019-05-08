package com.fahimshahrierrasel.moviedb.data.model


import com.google.gson.annotations.SerializedName

data class PersonCreditResponse(
    @SerializedName("cast")
    val cast: List<Cast>,
    @SerializedName("id")
    val id: Int
)

data class Cast(
    @SerializedName("id")
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: Any,
    @SerializedName("title")
    val title: String
)