package com.druide.flexmovies.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import com.druide.flexmovies.common.Resource
import com.druide.flexmovies.common.component.SectionItemByCategory
import com.druide.flexmovies.common.component.SectionItemByCategoryWithAction
import com.druide.flexmovies.common.navigation.FlexMoviesScreens
import com.druide.flexmovies.domain.model.Movies
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavHostController, moviesViewModel: MoviesViewModel) {

    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
        MainContent(
            navController = navController,
            moviesViewModel,
            paddingValues
        )
    }

}



@Composable
fun MainContent(
    navController: NavHostController,
    moviesViewModel: MoviesViewModel,
    paddingValues: PaddingValues
) {
    LaunchedEffect(true){
        moviesViewModel.getAllMoviesContent()
    }
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        item {
            PopularMovies(moviesViewModel = moviesViewModel, navController = navController)
        }

        item {
            TopRatedMovies(moviesViewModel, navController)
        }

        item {
            NowPlayingMovies(moviesViewModel = moviesViewModel, navController = navController )
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
            DisplaySectionContent((movieState as Resource.Success).data as Movies, navController, "Popular Movies")

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
            DisplaySectionContent((movieState as Resource.Success).data as Movies, navController, "Top Rated Movies")

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
            DisplaySectionContent((movieState as Resource.Success).data as Movies, navController, "Now Playinh Movies")

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
            DisplaySectionContent((movieState as Resource.Success).data as Movies, navController, "Up Coming Movies")

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
            DisplaySectionContent((movieState as Resource.Success).data as Movies, navController, "Latest Movies")

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
            navController.navigate(FlexMoviesScreens.MovieDetailScreen.route + "/${id}")
        
    }, onSeeAll = {
            Toast.makeText(context, "Sell ALL", Toast.LENGTH_SHORT).show()
    })
}




