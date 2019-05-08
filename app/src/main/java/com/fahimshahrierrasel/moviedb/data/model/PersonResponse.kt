package com.fahimshahrierrasel.moviedb.data.model


import com.google.gson.annotations.SerializedName

data class PersonResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<PersonResult>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class PersonResult(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("popularity")
    val popularity: Double,
    @SerializedName("profile_path")
    val profilePath: String
)