package com.druide.flexmovies.presentation.home

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.druide.flexmovies.common.navigation.HomeNavigation
import com.druide.flexmovies.common.navigation.HomeScreens


@Composable
public fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route?.substringBefore("/")
}

@Composable
fun HomeScreen(padding: PaddingValues) {
    val navController = rememberNavController()
    var showTabs by rememberSaveable { mutableStateOf(true) }
    
    showTabs = currentRoute(navController = navController) != HomeScreens.DetailContentScreen.route

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .padding(bottom = padding.calculateBottomPadding())
    ) {
        AnimatedVisibility(visible = showTabs, enter = fadeIn(), exit =  fadeOut()) {
            HomeSectionTabScreen(navController)
        }
        HomeNavigation(navController)
    }


}

@Composable
fun HomeSectionTabScreen(navController: NavHostController) {
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf(
        HomeScreens.MoviesScreen,
        HomeScreens.TvShowScreen,
        HomeScreens.AnimeScreen,
        HomeScreens.MyListScreen,
    )

    CustomScrollableTabRow(
        tabs = tabs,
        selectedTabIndex = selectedTabIndex,
    ) { tabIndex ->
        selectedTabIndex = tabIndex
        navController.navigate(tabs[tabIndex].route)
    }

}

@Composable
fun CustomScrollableTabRow(
    tabs: List<HomeScreens>,
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit
) {
    val density = LocalDensity.current
    val tabWidths = remember {
        val tabWidthStateList = mutableStateListOf<Dp>()
        repeat(tabs.size) {
            tabWidthStateList.add(0.dp)
        }
        tabWidthStateList
    }
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        contentColor = Color.White,
        edgePadding = 0.dp,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(
                    currentTabPosition = tabPositions[selectedTabIndex],
                    tabWidth = tabWidths[selectedTabIndex]
                )
            )
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        tabs.forEachIndexed { tabIndex, tab ->
            Tab(
                selected = selectedTabIndex == tabIndex,
                onClick = { onTabClick(tabIndex) },

                text = {
                    Text(
                        text = tab.title,
                        onTextLayout = { textLayoutResult ->
                            tabWidths[tabIndex] =
                                with(density) { textLayoutResult.size.width.toDp() }
                        }
                    )
                },
                unselectedContentColor = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.5F),
                selectedContentColor =  MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

fun Modifier.customTabIndicatorOffset(
    currentTabPosition: TabPosition,
    tabWidth: Dp
): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "customTabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val currentTabWidth by animateDpAsState(
        targetValue = tabWidth,
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    val indicatorOffset by animateDpAsState(
        targetValue = ((currentTabPosition.left + currentTabPosition.right - tabWidth) / 2),
        animationSpec = tween(durationMillis = 250, easing = FastOutSlowInEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(currentTabWidth)
}





