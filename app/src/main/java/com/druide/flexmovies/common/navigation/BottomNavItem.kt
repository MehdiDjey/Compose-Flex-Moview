package com.druide.flexmovies.common.navigation

import com.druide.flexmovies.R

sealed class BottomNavItem(var title:String, var icon:Int, var screen_route:String) {

    object Home : BottomNavItem("Home", android.R.drawable.arrow_up_float, "home")
    object Playing : BottomNavItem("Playing", android.R.drawable.arrow_up_float, "playing")
    object Search : BottomNavItem("search", android.R.drawable.arrow_up_float, "search")
    object Favorites : BottomNavItem("Favorites", android.R.drawable.arrow_up_float, "favorite")
    object Profile : BottomNavItem("Profile", android.R.drawable.arrow_up_float, "profile")
}
