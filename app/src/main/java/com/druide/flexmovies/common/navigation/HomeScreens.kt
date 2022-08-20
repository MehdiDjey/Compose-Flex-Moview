package com.druide.flexmovies.common.navigation

sealed class HomeScreens(val title: String, val route: String) {
    object MoviesScreen : HomeScreens("Movies", "movie_screen")
    object TvShowScreen : HomeScreens("Tv Show", "tv_show_screen")
    object AnimeScreen : HomeScreens("Anime", "anime_screen")
    object MyListScreen : HomeScreens("My List", "my_list_screen")
    object DetailContentScreen : HomeScreens("Details", "details_content_screen")
}
