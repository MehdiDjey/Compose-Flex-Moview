package com.druide.flexmovies.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.druide.flexmovies.common.component.BottomBarNavigation
import com.druide.flexmovies.common.navigation.MainNavigationGraph
import com.druide.flexmovies.ui.theme.FlexMoviesTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlexMoviesTheme {
                MainScreenView()
            }
        }
    }
}


@Composable
fun MainScreenView() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBarNavigation(navController = navController) },
        content = { padding: PaddingValues ->
            MainNavigationGraph(padding, navController)
        }
    )
}






