package com.druide.flexmovies.common.navigation

sealed class FlexMoviesScreens(val route : String) {
    object HomeScreen : FlexMoviesScreens("home_screen")
    object MovieDetailScreen : FlexMoviesScreens("movie_detail_screen")
}
