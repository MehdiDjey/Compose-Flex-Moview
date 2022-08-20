package com.druide.flexmovies.common.navigation

import com.druide.flexmovies.R

sealed class BottomNavItem(var title: String, var icon: Int, var screen_route: String) {

    object Home : BottomNavItem("Home", R.drawable.ic_round_home_24, "home")
    object Playing : BottomNavItem("Playing", R.drawable.ic_round_play_circle_filled_24, "playing")
    object Search : BottomNavItem("search", R.drawable.ic_round_search_24, "search")
    object Favorites : BottomNavItem("Favorites", R.drawable.ic_round_favorite_24, "favorite")
    object Profile : BottomNavItem("Profile", R.drawable.ic_round_person_24, "profile")
}
