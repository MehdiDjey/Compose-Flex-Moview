package com.druide.flexmovies.presentation.home

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.druide.flexmovies.common.Resource
import com.druide.flexmovies.common.component.ExtraSmallSpacer
import com.druide.flexmovies.common.component.MediumSpacer
import com.druide.flexmovies.common.navigation.FlexMoviesScreens
import com.druide.flexmovies.domain.model.Movies
import com.druide.flexmovies.domain.model.Results
import com.druide.flexmovies.formattedPosterPath

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavHostController, moviesViewModel: MoviesViewModel) {

    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Transparent, elevation =
            0.dp, modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = "Movies", fontWeight = FontWeight.Bold)
        }
    }) {
        MainContent(
            navController = navController,
            moviesViewModel
        )

    }
}


@Composable
fun MainContent(navController: NavHostController, moviesViewModel: MoviesViewModel) {

    val context = LocalContext.current
    LazyColumn {
        item {
            val movieState by moviesViewModel.moviesState.collectAsState(Resource.Empty)

            LaunchedEffect(true) {
                moviesViewModel.getPopularMovies()
            }

            when (movieState) {
                Resource.Empty -> Toast.makeText(context, "Empt", Toast.LENGTH_SHORT).show()
                is Resource.Error -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                Resource.Loading -> Toast.makeText(context, "Load", Toast.LENGTH_SHORT).show()
                is Resource.Success -> SectionItem(
                    sectionTitle = "Most popular movies",
                    movies = (movieState as Resource.Success).data as Movies,
                    navController = navController
                )
            }

            MediumSpacer()
        }

        item {
            val tvShowState by moviesViewModel.tvShowState.collectAsState(Resource.Empty)

            LaunchedEffect(true) {
                moviesViewModel.getPopularTvShow()
            }

            when (tvShowState) {
                Resource.Empty -> Toast.makeText(context, "Empt", Toast.LENGTH_SHORT).show()
                is Resource.Error -> Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                Resource.Loading -> Toast.makeText(context, "Load", Toast.LENGTH_SHORT).show()
                is Resource.Success -> SectionItem(
                    sectionTitle = "Most popular tv show",
                    movies = (tvShowState as Resource.Success).data as Movies,
                    navController = navController
                )
            }

        }

    }
}


@Composable
fun SectionItem(sectionTitle: String, movies: Movies, navController: NavHostController) {
    Column() {
        Text(text = sectionTitle)
        val scrollState = rememberScrollState()
        LazyRow(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(16.dp)) {
            items(movies.results) { item: Results ->
                ItemMovieCard(item, onItemClicked = { movie ->
                    navController.navigate(FlexMoviesScreens.MovieDetailScreen.route + "/${movie.id}")
                })
            }
        }
    }
}

@Composable
fun ItemMovieCard(movie: Results, onItemClicked: (movie: Results) -> Unit) {
    val imgSrc = movie.posterPath?.formattedPosterPath()

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = { onItemClicked(movie) }),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colors.onSurface
    ) {
        Column(
            modifier = Modifier
                .width(130.dp)
                .wrapContentSize()
        ) {
            AsyncImage(
                model = imgSrc,
                contentDescription = null,
                modifier = Modifier,
                contentScale = ContentScale.Fit,
            )


            Text(
                text = (movie.title ?: movie.name)!!, style = MaterialTheme.typography.caption,
                color = MaterialTheme.colors.surface,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(6.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
                )
            ExtraSmallSpacer()
        }

    }

}

@Composable
fun PopularContent() {

}

@Composable
fun FreeContent() {

}

@Composable
fun TrendingContent() {

}

