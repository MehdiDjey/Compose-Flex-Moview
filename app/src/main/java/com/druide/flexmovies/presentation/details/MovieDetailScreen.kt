package com.druide.flexmovies.presentation.details

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MovieDetailScreen(navController: NavHostController, int: Int?) {
    Scaffold(topBar = {
        TopAppBar(
            backgroundColor = Color.Transparent, elevation =
            0.dp, modifier = Modifier.padding(start = 8.dp)
        ) {
            Text(text = "Details $int", fontWeight = FontWeight.Bold)
        }
    }){}

}