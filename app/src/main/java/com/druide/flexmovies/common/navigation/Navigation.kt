package com.druide.flexmovies.common.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.druide.flexmovies.presentation.details.MovieDetailScreen
import com.druide.flexmovies.presentation.home.HomeScreen
import com.druide.flexmovies.presentation.home.MoviesViewModel


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = FlexMoviesScreens.HomeScreen.route) {

        composable(route = FlexMoviesScreens.HomeScreen.route) {
            val moviesViewModel =  hiltViewModel<MoviesViewModel>()
            HomeScreen(navController, moviesViewModel)
        }

        composable(route = FlexMoviesScreens.MovieDetailScreen.route + "/{idMovie}",
            arguments = listOf(navArgument(name = "idMovie") {
                type = NavType.IntType
            })
        ) { entry ->
            MovieDetailScreen(navController, entry.arguments?.getInt("idMovie"))
        }
    }

}