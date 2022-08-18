package com.druide.flexmovies.presentation.home

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.ScrollableTabRow
import androidx.compose.material.Tab
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.druide.flexmovies.common.Resource
import com.druide.flexmovies.common.component.SectionItemByCategoryWithAction
import com.druide.flexmovies.common.navigation.FlexMoviesScreens
import com.druide.flexmovies.common.navigation.TabsItem
import com.druide.flexmovies.domain.model.Movies
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(navController: NavHostController, moviesViewModel: MoviesViewModel) {

    Scaffold(
        backgroundColor = MaterialTheme.colorScheme.surface
    ) { paddingValues ->
     /*   MainContent(
            navController = navController,
            moviesViewModel,
            paddingValues
        )*/

        SectionTabScreen(paddingValues)
    }

}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SectionTabScreen(paddingValues: PaddingValues) {

    val tabs = listOf(
        TabsItem.MoviesScreen,
        TabsItem.TvShowScreen,
        TabsItem.AnimeScreen,
        TabsItem.MyListScreen,
        )

    val pagerState = rememberPagerState()


        Column {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }

}


@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<TabsItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    // OR ScrollableTabRow()
    ScrollableTabRow(
        modifier = Modifier.fillMaxWidth(),
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = MaterialTheme.colorScheme.background,
//        contentColor = Color.White,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            androidx.compose.material.TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }){

        tabs.forEachIndexed { index, tab ->
            // OR LeadingIconTab()

            Tab(
                text = {
                    Text(
                        text = tab.title,
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        color = MaterialTheme.colorScheme.onBackground
                    )},
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                },
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<TabsItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, count = tabs.size) { page ->
        tabs[page].screen()
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




