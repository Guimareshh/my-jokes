package com.lucienguimaraes.design_system.typography

import androidx.compose.material.Typography
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

private val defaultTypography = Typography()

val appTypography = Typography(
    h1 = defaultTypography.h1.copy(letterSpacing = TextUnit.Unspecified),
    h2 = defaultTypography.h2.copy(
        letterSpacing = TextUnit.Unspecified,
        fontSize = 26.sp,
        fontWeight = FontWeight.Bold,
    ),
    h3 = defaultTypography.h3.copy(
        letterSpacing = TextUnit.Unspecified,
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
    ),
    h4 = defaultTypography.h4.copy(
        letterSpacing = TextUnit.Unspecified,
        fontSize = 22.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    h5 = defaultTypography.h5.copy(
        letterSpacing = TextUnit.Unspecified,
        fontSize = 18.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    h6 = defaultTypography.h6.copy(letterSpacing = TextUnit.Unspecified, fontSize = 18.sp),
    subtitle1 = defaultTypography.subtitle1.copy(
        letterSpacing = TextUnit.Unspecified,
        fontWeight = FontWeight.Medium,
    ),
    subtitle2 = defaultTypography.subtitle2.copy(
        letterSpacing = TextUnit.Unspecified,
        fontWeight = FontWeight.Medium,
    ),
    body1 = defaultTypography.body1.copy(letterSpacing = TextUnit.Unspecified, fontSize = 18.sp),
    body2 = defaultTypography.body2.copy(letterSpacing = TextUnit.Unspecified, fontSize = 16.sp),
    button = defaultTypography.button.copy(letterSpacing = TextUnit.Unspecified),
    caption = defaultTypography.caption.copy(letterSpacing = TextUnit.Unspecified),
    overline = defaultTypography.overline.copy(letterSpacing = TextUnit.Unspecified),
)
