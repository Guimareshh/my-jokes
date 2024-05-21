package com.lucienguimaraes.design_system.button

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun rememberButtonState(enable: Boolean = true, loading: Boolean = false): ButtonState =
    remember { ButtonState(enable = enable, loading = loading) }

@Stable
class ButtonState(enable: Boolean, loading: Boolean) {
    var enable by mutableStateOf(enable)
    var loading by mutableStateOf(loading)

    fun canBeClicked() = enable && loading.not()
}
