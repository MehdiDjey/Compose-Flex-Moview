package com.druide.flexmovies.common.navigation

import androidx.compose.runtime.Composable
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.presentation.main.Home

typealias ComposableFun = @Composable () -> Unit

sealed class TabsItem(val title: String, val screen: ComposableFun){
    object MoviesScreen: TabsItem(title = "Movies", { Home() })
    object TvShowScreen: TabsItem(title = "TV Shows", { Home()})
    object AnimeScreen: TabsItem(title = "Anime", { Home()})
    object MyListScreen: TabsItem(title = "My List", { Home()})
}