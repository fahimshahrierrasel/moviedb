package com.fahimshahrierrasel.moviedb.helper

const val API_BASE_URL = "https://api.themoviedb.org/3/"
const val apiKey = "c2881bfd23e0da43006d5456bb4583b6"

const val posterPrefix = "https://image.tmdb.org/t/p/w154"
const val backdropPrefix = "https://image.tmdb.org/t/p/w780"
const val castProfilePrefix = "https://image.tmdb.org/t/p/w276_and_h350_face"


const val GITHUB_URL = "https://github.com/fahimshahrierrasel/moviedb"


// Key Constant
const val MOVIE_ID = "MOVIE_ID"
const val GENRE_ID = "GENRE_ID"
const val PERSON_ID = "PERSON_ID"
const val GENRE_NAME = "GENRE_NAME"
const val MOVIE_KEYWORD = "MOVIE_KEYWORD"

val cardColors = listOf("#00BCD4", "#03A9F4", "#00E676", "#FFEB3B", "#FFC400", "#FFC400", "#FF3D00")

enum class SearchMode {
    NORMAL,
    ADVANCE
}
