package com.lucienguimaraes.myjokes.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource

@Composable
fun AppBottomBar(
    modifier: Modifier = Modifier,
    currentTopScreen: Screen,
    onScreenSelected: (Screen) -> Unit,
) {
    NavigationBar(modifier = modifier, contentColor = MaterialTheme.colorScheme.surface) {
        AppNavigationItemLists.forEach { item ->
            NavigationBarItem(
                icon = { Icon(imageVector = (item.icon), null) },
                label = {
                    Text(
                        text = stringResource(item.title),
                        style = MaterialTheme.typography.labelMedium,
                    )
                },
                selected = currentTopScreen == item.screen,
                onClick = { onScreenSelected(item.screen) },
                colors = AppNavigationItemColors,
            )
        }
    }
}
