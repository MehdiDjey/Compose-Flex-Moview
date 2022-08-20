package com.druide.flexmovies.presentation.home.tvShow

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
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
fun TvShowScreen(navController: NavHostController, tvShowViewModel: TvShowViewModel) {
    LaunchedEffect(true ) {
        tvShowViewModel.getAllTvShowContent()
    }

    TvMainContent(navController, tvShowViewModel)
}


@Composable
fun TvMainContent(navController: NavHostController, tvShowViewModel: TvShowViewModel) {

    LazyColumn(modifier = Modifier
        .fillMaxHeight()
        .background(MaterialTheme.colorScheme.background)) {
        item{
            PopularTvShow(navController, tvShowViewModel)
        }
        item {
            TopRatedTvShow(navController, tvShowViewModel)
        }

        item {
            OnTheAirTvShow(navController = navController, tvShowViewModel = tvShowViewModel)
        }

        item {
            TodayAiringTvShow(navController = navController, tvShowViewModel = tvShowViewModel )
        }

        item {
            LatestTvShow(navController, tvShowViewModel)
        }

    }

}

@Composable
fun PopularTvShow(navController: NavHostController, tvShowViewModel: TvShowViewModel) {
    val tvState by tvShowViewModel.popularState.collectAsState(Resource.Empty)
    when (tvState) {
        Resource.Empty -> {}
        is Resource.Error -> {}
        Resource.Loading -> {}
        is Resource.Success ->
          DisplaySectionContent(
                (tvState as Resource.Success).data as Movies,
                navController,
                "Popular Tv Show"
            )

    }

}


@Composable
fun TopRatedTvShow(navController: NavHostController, tvShowViewModel: TvShowViewModel) {
    val tvState by tvShowViewModel.topRated.collectAsState(Resource.Empty)
    when (tvState) {
        Resource.Empty -> {}
        is Resource.Error -> {}
        Resource.Loading -> {}
        is Resource.Success ->
            DisplaySectionContent(
                (tvState as Resource.Success).data as Movies,
                navController,
                "Top Rated Tv Show"
            )

    }
}

@Composable
fun LatestTvShow(navController: NavHostController, tvShowViewModel: TvShowViewModel) {
    val tvState by tvShowViewModel.latestState.collectAsState(Resource.Empty)
    when (tvState) {
        Resource.Empty -> {}
        is Resource.Error -> {}
        Resource.Loading -> {}
        is Resource.Success ->
            DisplaySectionContent(
                (tvState as Resource.Success).data as Movies,
                navController,
                "Latest Tv Show"
            )

    }
}

@Composable
fun TodayAiringTvShow(navController: NavHostController, tvShowViewModel: TvShowViewModel) {
    val tvState by tvShowViewModel.todayAiringState.collectAsState(Resource.Empty)
    when (tvState) {
        Resource.Empty -> {}
        is Resource.Error -> {}
        Resource.Loading -> {}
        is Resource.Success ->
            DisplaySectionContent(
                (tvState as Resource.Success).data as Movies,
                navController,
                "Today Airing Tv Show"
            )

    }
}

@Composable
fun OnTheAirTvShow(navController: NavHostController, tvShowViewModel: TvShowViewModel) {
    val tvState by tvShowViewModel.onTheAirState.collectAsState(Resource.Empty)
    when (tvState) {
        Resource.Empty -> {}
        is Resource.Error -> {}
        Resource.Loading -> {}
        is Resource.Success ->
            DisplaySectionContent(
                (tvState as Resource.Success).data as Movies,
                navController,
                "On The AirTv Show"
            )

    }
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
