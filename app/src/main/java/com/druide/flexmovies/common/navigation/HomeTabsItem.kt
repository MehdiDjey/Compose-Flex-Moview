package com.druide.flexmovies.common.navigation

import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit

/*
sealed class HomeTabsItem(val title: String, val screen: ComposableFun){
    object MoviesScreen: HomeTabsItem(title = "Movies", { HomeNavigation()})
    object TvShowScreen: HomeTabsItem(title = "TV Shows", { TvShow(navController) })
    object AnimeScreen: HomeTabsItem(title = "Anime", { Anime(navController) })
    object MyListScreen: HomeTabsItem(title = "My List", { MyList(navController) })
}*/
