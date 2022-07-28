package com.druide.flexmovies.presentation.details

import android.annotation.SuppressLint
import android.media.effect.EffectFactory
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.druide.flexmovies.common.Resource
import com.druide.flexmovies.common.component.DefaultSpacer
import com.druide.flexmovies.common.component.ExtraSmallSpacer
import com.druide.flexmovies.common.component.SmallSpacer
import com.druide.flexmovies.domain.model.Credit
import com.druide.flexmovies.domain.model.Movie
import com.druide.flexmovies.formattedBackDropPath
import com.druide.flexmovies.formattedPosterPath


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

    LaunchedEffect(true) {
        if (int != null) {
            movieDetailViewModel.getMovieDetail(int)
            movieDetailViewModel.getMovieCast()
        }
    }

    Scaffold(topBar = {
    }) {
        when (movieState) {
            Resource.Empty -> {}
            is Resource.Error -> {}
            Resource.Loading -> {}
            is Resource.Success -> {
                DetailsContent((movieState as Resource.Success).data as Movie, creditState)
            }
        }
    }
}

@Composable
fun DetailsContent(movie: Movie, creditState: Resource) {
    Column(  modifier = Modifier
        .verticalScroll(rememberScrollState())) {
        DetailsHeader(
            imgSrc = movie.backdropPath?.formattedBackDropPath()!!,
            containerHeight = 500.dp
        )

        ExtraSmallSpacer()

        DetailsBookmark(movie)

        ExtraSmallSpacer()

        DetailsTitle(movie)

        ExtraSmallSpacer()

        Overview(movie)

        ExtraSmallSpacer()

        Cast(creditState)
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
    Row(modifier = Modifier.padding(12.dp)) {
        Icon(
            modifier = Modifier,
            tint = Color.Yellow,
            imageVector = Icons.Filled.Star,
            contentDescription = "Cover"
        )

        Text(text = "${movie.voteAverage}")

        Text(text = " | ${movie.popularity}")

        Icon(
            modifier = Modifier,
            tint = Color.Gray,
            imageVector = Icons.Outlined.FavoriteBorder,
            contentDescription = "Cover"
        )


        Icon(
            modifier = Modifier,
            tint = Color.Gray,
            imageVector = Icons.Outlined.Share,
            contentDescription = "Cover"
        )

    }

}

@Composable
fun DetailsTitle(movie: Movie) {
    Column(modifier = Modifier.padding(12.dp)) {
        Text(text = movie.title)
        Text(text = movie.genres.joinToString { it.name } + "•" + movie.releaseDate)

    }
}

@Composable
fun Overview(movie: Movie) {
    Column(modifier = Modifier.padding(12.dp)) {
        Text(text = "Overview")
        Text(text = movie.overview ?: "Not available at the moment")
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
            Column (modifier = Modifier.padding(12.dp)){
                Text(text = "Top Cast")

                ExtraSmallSpacer()

                LazyRow( horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    items(items = cast, itemContent = {
                        Column(modifier =  Modifier.align(Alignment.CenterHorizontally)) {
                            AsyncImage(
                                model = ImageRequest.Builder(LocalContext.current)
                                    .data(it.profilePath?.formattedPosterPath())
                                    .crossfade(true)
                                    .build(),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(64.dp)
                                    .clip(CircleShape).align(Alignment.CenterHorizontally),
                            )

                            DefaultSpacer()
                            Text(text = it.name?.replace(" ", "\n") ?: "", textAlign = TextAlign.Center)
                        }
                    })

                }
            }

        }
    }


}
