package com.druide.flexmovies.presentation.details

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.MoreVert
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.druide.flexmovies.common.Resource
import com.druide.flexmovies.common.component.*
import com.druide.flexmovies.common.navigation.FlexMoviesScreens
import com.druide.flexmovies.domain.model.Cast
import com.druide.flexmovies.domain.model.Credit
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.domain.model.Movies
import com.druide.flexmovies.formattedBackDropPath


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(
    navController: NavHostController,
    int: Int?,
    movieDetailViewModel: MovieDetailViewModel
) {
    val context = LocalContext.current
    val movieState by movieDetailViewModel.movieDetailState.collectAsState(Resource.Empty)
    val creditState by movieDetailViewModel.movieCastState.collectAsState(Resource.Empty)
    val similarMovieState by movieDetailViewModel.movieSimilarState.collectAsState(Resource.Empty)

    LaunchedEffect(true) {
        if (int != null) {
            movieDetailViewModel.getMovieDetail(int)
            movieDetailViewModel.getMovieCast()
            movieDetailViewModel.getSimilarMovies()
        }
    }

    Scaffold(topBar = {
    }) {
        when (movieState) {
            Resource.Empty -> {}
            is Resource.Error -> {}
            Resource.Loading -> {}
            is Resource.Success -> {
                DisplayDetailsContent((movieState as Resource.Success).data as Movie, creditState, similarMovieState, navController)
            }
        }
    }
}

@Composable
fun DisplayDetailsContent(movie: Movie, creditState: Resource, similarMovieState: Resource, navController: NavHostController) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(colorScheme.background)
    ) {
        DetailsHeader(
            imgSrc = movie.backdropPath?.formattedBackDropPath()!!,
            containerHeight = 500.dp
        )

        DefaultSpacer()

        DetailsBookmark(movie)

        DefaultSpacer()

        DetailsTitle(movie)

        DefaultSpacer()

        Overview(movie)

        DefaultSpacer()

        Cast(creditState)

        DefaultSpacer()

        SimilarMovies(similarMovieState = similarMovieState , navController)

        DefaultSpacer()

        DetailsActions()
    }


}


@Composable
fun DetailsHeader(imgSrc: String, containerHeight: Dp) {
    AsyncImage(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxWidth(),
        model = imgSrc,
        contentScale = ContentScale.Fit,
        contentDescription = null
    )
}

@Composable
fun DetailsBookmark(movie: Movie) {

    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {

        Icon(
            modifier = Modifier
                .size(18.dp)
                .align(CenterVertically),
            tint = Color.Yellow,
            imageVector = Icons.Filled.Star,
            contentDescription = "Cover"
        )

        Text(
            text = "${movie.voteAverage}",
            modifier = Modifier.align(CenterVertically),
            style = typography.labelSmall,
            color = colorScheme.onBackground
        )

        Text(
            text = " | ${movie.popularity}", modifier = Modifier.align(CenterVertically),
            style = typography.labelMedium,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            modifier = Modifier.align(CenterVertically),
            tint = Color.Gray,
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = "Cover"
        )

        Spacer(modifier = Modifier.size(12.dp))

        Icon(
            modifier = Modifier.align(CenterVertically),
            tint = Color.Gray,
            imageVector = Icons.Outlined.Share,
            contentDescription = "Cover"
        )

        Spacer(modifier = Modifier.size(12.dp))

        Icon(
            modifier = Modifier.align(CenterVertically),
            tint = Color.Gray,
            imageVector = Icons.Outlined.MoreVert,
            contentDescription = "Cover"
        )

    }

}

@Composable
fun DetailsTitle(movie: Movie) {
    Column(modifier = Modifier.padding(12.dp)) {
        Text(
            text = movie.title,
            style = typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = colorScheme.onBackground
        )

        Text(text = movie.genres.joinToString { it.name } + " • " + movie.releaseDate,
            style = typography.labelSmall,
            color = colorScheme.onSurfaceVariant
        )

    }
}

@Composable
fun Overview(movie: Movie) {
    Column(modifier = Modifier.padding(12.dp)) {
        Text(text = "Overview", style = typography.titleMedium, color = colorScheme.onBackground)
        Text(
            text = movie.overview ?: "Not available at the moment",
            style = typography.bodySmall,
            color = colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun Cast(creditState: Resource) {

    when (creditState) {
        Resource.Empty -> {

        }
        is Resource.Error -> {

        }
        Resource.Loading -> {

        }
        is Resource.Success -> {
            val cast = (creditState.data as Credit).cast
            Column(modifier = Modifier.padding(12.dp)) {
                Text(
                    text = "Top Cast",
                    style = typography.titleMedium,
                    color = colorScheme.onBackground
                )
                ExtraSmallSpacer()
              DisplayCast(cast, Modifier.align(Alignment.CenterHorizontally))
            }

        }
    }

}


@Composable
fun DisplayCast(cast: List<Cast>, modifier: Modifier = Modifier) {
    LazyRow(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
        items(items = cast, itemContent = {
            RoundedItem( it , modifier = modifier)
        })
    }
}


@Composable
fun DetailsActions() {

    Row(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.Bottom
    ) {

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .weight(1f)
                .height(60.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.primary
            )
        ) {
            Text(
                "TRAILER",
                style = typography.titleMedium.copy(color = colorScheme.onPrimary)
            )
        }

        Spacer(modifier = Modifier.size(20.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .weight(1F)
                .height(60.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorScheme.primary
            )
        ) {
            Icon(Icons.Default.PlayArrow, contentDescription = null)
            Text(
                "PLAY NOW",
                style = typography.titleMedium.copy(color = colorScheme.onPrimary)
            )
        }
    }
}


@Composable
fun SimilarMovies(similarMovieState: Resource, navController: NavHostController) {

    when(similarMovieState) {
        Resource.Empty -> {}
        is Resource.Error -> {}
        Resource.Loading ->{}
        is Resource.Success ->  {
            DisplaySimilarMovies(similarMovieState.data as Movies, navController)
        }
    }
}

@Composable
fun DisplaySimilarMovies(movies: Movies, navController: NavHostController) {
    SectionItemByCategory(
        categoryTitle = "Who might interest you",
        movies = movies
    ) {
        navController.navigate(FlexMoviesScreens.MovieDetailScreen.route + "/${it}")

    }
}
