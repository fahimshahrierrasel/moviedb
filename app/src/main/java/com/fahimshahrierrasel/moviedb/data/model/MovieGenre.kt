package com.fahimshahrierrasel.moviedb.data.model
import com.google.gson.annotations.SerializedName


data class MovieGenre(
    @SerializedName("genres")
    val genres: List<Genre>
)

data class Genre(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)