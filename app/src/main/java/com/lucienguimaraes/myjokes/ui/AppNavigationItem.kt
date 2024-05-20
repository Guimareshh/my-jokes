package com.lucienguimaraes.myjokes.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.ui.graphics.vector.ImageVector
import com.lucienguimaraes.design_system.MyJokesColor
import com.lucienguimaraes.design_system.R

internal sealed class AppNavigationItem(
    val screen: Screen,
    @StringRes val title: Int,
    val icon: ImageVector,
) {
    data object Home : AppNavigationItem(Screen.Home, R.string.home_tab_title, Icons.Rounded.Home)
    data object Favorite : AppNavigationItem(Screen.Favorite, R.string.favorite_tab_title, Icons.Rounded.Favorite)
}

internal val AppNavigationItemLists = listOf(AppNavigationItem.Home, AppNavigationItem.Favorite)

internal val AppNavigationItemColors = NavigationBarItemColors(
    selectedIconColor = MyJokesColor.Green.sea,
    selectedTextColor = MyJokesColor.Green.sea,
    selectedIndicatorColor = MyJokesColor.Green.sea.copy(alpha = 0.2f),
    unselectedIconColor = MyJokesColor.Grey.lighten10,
    unselectedTextColor = MyJokesColor.Grey.lighten10,
    disabledIconColor = MyJokesColor.Grey.lighten10.copy(alpha = 0.2f),
    disabledTextColor = MyJokesColor.Grey.lighten10.copy(alpha = 0.2f),
)