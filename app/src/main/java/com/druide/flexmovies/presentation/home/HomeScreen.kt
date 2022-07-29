package com.druide.flexmovies.presentation.home

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.druide.flexmovies.common.Resource
import com.druide.flexmovies.common.component.ExtraSmallSpacer
import com.druide.flexmovies.common.component.MediumSpacer
import com.druide.flexmovies.common.navigation.FlexMoviesScreens
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.druide.flexmovies.domain.model.Results
import com.druide.flexmovies.formattedPosterPath


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

    val context = LocalContext.current
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
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
    Column( modifier = Modifier.padding(start = 12.dp, top = 8.dp)) {
        Text(
            text = sectionTitle,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
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

    MovieCard(movie, onItemClicked = onItemClicked)

/*    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable(onClick = { onItemClicked(movie) }),
        elevation = 0.dp,
        backgroundColor = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
        ) {
            AsyncImage(
                model = imgSrc,
                modifier = Modifier.weight(1F),
                contentDescription = null,
                contentScale = ContentScale.Fit,
            )


      *//*      Text(
                text = (movie.title ?: movie.name)!!,
                color = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(2.dp).weight(1F),
                style = MaterialTheme.typography.bodySmall
            )*//*

        }

    }*/

}

@Composable
fun MovieCard(movie: Results, isFirstCard: Boolean = false, modifier: Modifier = Modifier, onItemClicked: (movie: Results) -> Unit) {
    rowSpacer(value = if(isFirstCard) 16 else 4)
    val imgSrc = movie.posterPath?.formattedPosterPath()
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.width(150.dp).padding(vertical = 8.dp).wrapContentHeight()
    ) {
        Card(
            elevation = 4.dp,
            modifier = Modifier.clickable(onClick = { onItemClicked(movie) }),
            shape = MaterialTheme.shapes.medium
        ) {
            AsyncImage(
                model = imgSrc,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier.fillMaxWidth().aspectRatio(3 / 4F)
            )
        }
        columnSpacer(8)
        val padding = Modifier.padding(horizontal = 8.dp)
        Text(
            text = (movie.title ?: movie.name)!!,
            style =  MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 2,
            modifier = padding,
            textAlign = TextAlign.Center
        )
        columnSpacer(4)

        Spacer(Modifier.size(8.dp))
    }
    rowSpacer(value = 4)
}

@Suppress("unused")
@Composable
fun rowSpacer(value: Int) = Spacer(modifier = Modifier.size(value.dp))

@Composable
fun columnSpacer(value: Int) = Spacer(modifier = Modifier.size(value.dp))


