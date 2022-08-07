package com.druide.flexmovies.common.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.druide.flexmovies.common.navigation.FlexMoviesScreens
import com.druide.flexmovies.domain.model.Cast
import com.druide.flexmovies.domain.model.Movies
import com.druide.flexmovies.domain.model.Results
import com.druide.flexmovies.formattedPosterPath



@Composable
fun CardItem(movie: Results, isFirstCard : Boolean= false, modifier: Modifier = Modifier,  onItemClicked: (movie: Results) -> Unit) {
    rowSpacer(value = if (isFirstCard) 16 else 4)
    val imgSrc = movie.posterPath?.formattedPosterPath()

    Card(modifier = Modifier
        .clickable(onClick = { onItemClicked(movie) })
        .width(120.dp)
        .fillMaxHeight(),
        shape = MaterialTheme.shapes.medium,
        elevation = 4.dp,) {


        Column() {
            AsyncImage(
                model = imgSrc,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3 / 4F)
            )

       /*     val padding = Modifier.padding(horizontal = 8.dp)
            Text(
                text = (movie.title ?: movie.name)!!,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 2,
                modifier = padding.align(alignment = CenterHorizontally),
                textAlign = TextAlign.Center,
            )
            columnSpacer(4)*/
        }
    }
    rowSpacer(value = 12)
   /*
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .width(150.dp)
            .padding(vertical = 8.dp)
            .wrapContentHeight()
    ) {
        Card(
            elevation = 4.dp,
            modifier = Modifier
                .clickable(onClick = { onItemClicked(movie) })
                .height(IntrinsicSize.Min)
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium
        ) {
            AsyncImage(
                model = imgSrc,
                contentDescription = null,
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(3 / 4F)
            )
        }
        columnSpacer(8)
        val padding = Modifier.padding(horizontal = 8.dp)
        Text(
            text = (movie.title ?: movie.name)!!,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 2,
            modifier = padding,
            textAlign = TextAlign.Center
        )
        columnSpacer(4)

        Spacer(Modifier.size(8.dp))
    }
    rowSpacer(value = 4)*/
}


@Composable
fun RoundedItem(cast: Cast, modifier: Modifier = Modifier, isFirstCard: Boolean = false) {
    rowSpacer(value = if (isFirstCard) 16 else 4)

    Column(modifier = modifier) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(cast.profilePath?.formattedPosterPath())
                .crossfade(true)
                .build(),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .align(Alignment.CenterHorizontally),
        )

        columnSpacer(8)

        Text(
            text = cast.name?.replace(" ", "\n") ?: "",
            textAlign = TextAlign.Center, style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        columnSpacer(4)

        Spacer(Modifier.size(8.dp))
    }

    rowSpacer(value = 4)
}


@Composable
fun SectionItemByCategory(
    categoryTitle: String,
    movies: Movies,
    onItemSelected: (itemId : Int) -> Unit
) {
    Column(modifier = Modifier.padding(start = 12.dp, top = 8.dp)) {
        Box() {

        }
        Text(
            text = categoryTitle,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        LazyRow(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(16.dp)) {
            items(movies.results) { result: Results ->
                CardItem(movie = result) { movie ->
                    onItemSelected(movie.id)
                }
            }
        }
    }
}


@Composable
fun SectionItemByCategoryWithAction(categoryTitle: String, movies: Movies,  onItemSelected: (itemId : Int) -> Unit, onSeeAll : () -> Unit ) {
    Column(modifier = Modifier.padding(start = 12.dp, top = 8.dp, end = 12.dp)) {
        var enabled by rememberSaveable{ mutableStateOf(true)}
        Box(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = categoryTitle,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Start,
                modifier = Modifier.align(Alignment.CenterStart)
            )

            Text(
                text = "See all",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.End,
                modifier = Modifier.align(Alignment.CenterEnd).clickable(enabled = enabled) {
                    enabled = false
                    onSeeAll()
                },
            )

        }

        LazyRow(modifier = Modifier.fillMaxWidth(), contentPadding = PaddingValues(16.dp)) {
            items(movies.results) { result: Results ->
                CardItem(movie = result) { movie ->
                    onItemSelected(movie.id)
                  //  navController.navigate(FlexMoviesScreens.MovieDetailScreen.route + "/${movie.id}")
                }
            }
        }
    }

}