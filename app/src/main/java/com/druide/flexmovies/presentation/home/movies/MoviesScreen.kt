package com.druide.flexmovies.presentation.home.movies

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.druide.flexmovies.common.Resource
import com.druide.flexmovies.common.component.SectionItemByCategoryWithAction
import com.druide.flexmovies.common.navigation.HomeScreens
import com.druide.flexmovies.domain.model.Movies


@Composable
fun MoviesScreen(navController: NavHostController, moviesViewModel: MoviesViewModel) {
    LaunchedEffect(true) {
        moviesViewModel.getAllMoviesContent()
    }
    MoviesMainContent(navController = navController, moviesViewModel = moviesViewModel)
}

@Composable
fun MoviesMainContent(
    navController: NavHostController,
    moviesViewModel: MoviesViewModel
) {

    LazyColumn(modifier = Modifier.fillMaxHeight().background(MaterialTheme.colorScheme.background)) {
        item {
            PopularMovies(moviesViewModel = moviesViewModel, navController = navController)
        }

        item {
            TopRatedMovies(moviesViewModel, navController)
        }

        item {
            NowPlayingMovies(moviesViewModel = moviesViewModel, navController = navController)
        }

        item {
            UpComingMovies(moviesViewModel = moviesViewModel, navController = navController)
        }

        item {
            LatestMovies(moviesViewModel = moviesViewModel, navController = navController)
        }

        item {
            PopularShow(moviesViewModel = moviesViewModel, navController = navController)
        }
    }
}


@Composable
fun PopularMovies(moviesViewModel: MoviesViewModel, navController: NavHostController) {
    val movieState by moviesViewModel.popularState.collectAsState(Resource.Empty)

    when (movieState) {
        Resource.Empty -> {}
        is Resource.Error -> {}
        Resource.Loading -> {}
        is Resource.Success ->
            DisplaySectionContent(
                (movieState as Resource.Success).data as Movies,
                navController,
                "Popular Movies"
            )

    }
}

@Composable
fun TopRatedMovies(moviesViewModel: MoviesViewModel, navController: NavHostController) {
    val movieState by moviesViewModel.topRatedState.collectAsState(Resource.Empty)

    when (movieState) {
        Resource.Empty -> {}
        is Resource.Error -> {}
        Resource.Loading -> {}
        is Resource.Success ->
            DisplaySectionContent(
                (movieState as Resource.Success).data as Movies,
                navController,
                "Top Rated Movies"
            )

    }
}

@Composable
fun NowPlayingMovies(moviesViewModel: MoviesViewModel, navController: NavHostController) {
    val movieState by moviesViewModel.nowPlayingState.collectAsState(Resource.Empty)

    when (movieState) {
        Resource.Empty -> {}
        is Resource.Error -> {}
        Resource.Loading -> {}
        is Resource.Success ->
            DisplaySectionContent(
                (movieState as Resource.Success).data as Movies,
                navController,
                "Now Playinh Movies"
            )

    }
}

@Composable
fun UpComingMovies(moviesViewModel: MoviesViewModel, navController: NavHostController) {
    val movieState by moviesViewModel.upComingState.collectAsState(Resource.Empty)

    when (movieState) {
        Resource.Empty -> {}
        is Resource.Error -> {}
        Resource.Loading -> {}
        is Resource.Success ->
            DisplaySectionContent(
                (movieState as Resource.Success).data as Movies,
                navController,
                "Up Coming Movies"
            )

    }
}

@Composable
fun LatestMovies(moviesViewModel: MoviesViewModel, navController: NavHostController) {
    val movieState by moviesViewModel.latestState.collectAsState(Resource.Empty)

    when (movieState) {
        Resource.Empty -> {}
        is Resource.Error -> {}
        Resource.Loading -> {}
        is Resource.Success ->
            DisplaySectionContent(
                (movieState as Resource.Success).data as Movies,
                navController,
                "Latest Movies"
            )

    }
}

@Composable
fun PopularShow(moviesViewModel: MoviesViewModel, navController: NavHostController) {
/*    val tvShowState by moviesViewModel.tvShowState.collectAsState(Resource.Empty)

    LaunchedEffect(true) {
        moviesViewModel.getPopularTvShow()
    }

    when (tvShowState) {
        Resource.Empty -> {}
        is Resource.Error -> {}
        Resource.Loading -> {}
        is Resource.Success -> DisplaySectionContent((tvShowState as Resource.Success).data as Movies, navController, "Popular Show")
    }*/
}

@Composable
fun DisplaySectionContent(movies: Movies, navController: NavHostController, title: String) {


    val context = LocalContext.current
    SectionItemByCategoryWithAction(title, movies,
        onItemSelected = { id: Int ->
            navController.navigate(HomeScreens.DetailContentScreen.route + "/${id}")

        }, onSeeAll = {
            Toast.makeText(context, "Sell ALL", Toast.LENGTH_SHORT).show()
        })
}
