package com.druide.flexmovies.common.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.druide.flexmovies.presentation.details.ContentDetailScreen
import com.druide.flexmovies.presentation.details.MovieDetailViewModel
import com.druide.flexmovies.presentation.home.HomeScreen
import com.druide.flexmovies.presentation.home.anime.Anime
import com.druide.flexmovies.presentation.home.movies.MoviesScreen
import com.druide.flexmovies.presentation.home.movies.MoviesViewModel
import com.druide.flexmovies.presentation.home.myList.MyList
import com.druide.flexmovies.presentation.home.tvShow.TvShowScreen
import com.druide.flexmovies.presentation.home.tvShow.TvShowViewModel


@Composable
fun HomeNavigation(navController: NavHostController) {


    NavHost(navController = navController, startDestination = HomeScreens.MoviesScreen.route) {

        composable(route = HomeScreens.MoviesScreen.route) {
            val moviesViewModel = hiltViewModel<MoviesViewModel>()
            MoviesScreen(navController, moviesViewModel)
        }

        composable(route = HomeScreens.TvShowScreen.route) {
            val tvShowViewModel  =  hiltViewModel<TvShowViewModel>()
            TvShowScreen(navController, tvShowViewModel)
        }


        composable(route = HomeScreens.AnimeScreen.route) {
            Anime(navController)
        }

        composable(route = HomeScreens.MyListScreen.route) {
            MyList(navController)
        }

        composable(
            route = HomeScreens.DetailContentScreen.route + "/{id}",
            arguments = listOf(navArgument(name = "id") {
                type = NavType.IntType
            })
        ) { entry ->
            val movieDetailViewModel = hiltViewModel<MovieDetailViewModel>()
            ContentDetailScreen(navController, entry.arguments?.getInt("id"), movieDetailViewModel)
        }
    }

}


@Composable
fun MainNavigationGraph(modifier: PaddingValues, navController: NavHostController) {

    NavHost(navController = navController, startDestination = BottomNavItem.Home.screen_route) {
        composable(BottomNavItem.Home.screen_route) {
            HomeScreen(modifier)
        }
        composable(BottomNavItem.Playing.screen_route) {
            // NetworkScreen()
        }
        composable(BottomNavItem.Search.screen_route) {
            //AddPostScreen()
        }
        composable(BottomNavItem.Favorites.screen_route) {
            //NotificationScreen()
        }
        composable(BottomNavItem.Playing.screen_route) {
            //JobScreen()
        }

        composable(BottomNavItem.Profile.screen_route) {
            //ProfileScreen()
        }
    }
}
