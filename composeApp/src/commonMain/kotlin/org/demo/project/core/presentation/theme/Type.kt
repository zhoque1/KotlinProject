package org.demo.project.core.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.outfit_black
import kotlinproject.composeapp.generated.resources.outfit_bold
import kotlinproject.composeapp.generated.resources.outfit_extrabold
import kotlinproject.composeapp.generated.resources.outfit_extralight
import kotlinproject.composeapp.generated.resources.outfit_light
import kotlinproject.composeapp.generated.resources.outfit_medium
import kotlinproject.composeapp.generated.resources.outfit_regular
import kotlinproject.composeapp.generated.resources.outfit_semibold
import kotlinproject.composeapp.generated.resources.outfit_thin
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.Font


@OptIn(ExperimentalResourceApi::class)
@Composable
fun CustomFontFamily()  = FontFamily(
    listOf(
        Font(Res.font.outfit_regular, FontWeight.Normal),
        Font(Res.font.outfit_black, FontWeight.Black),
        Font(Res.font.outfit_bold, FontWeight.Bold),
        Font(Res.font.outfit_extrabold, FontWeight.ExtraBold),
        Font(Res.font.outfit_extralight, FontWeight.ExtraLight),
        Font(Res.font.outfit_light, FontWeight.Light),
        Font(Res.font.outfit_medium, FontWeight.Medium),
        Font(Res.font.outfit_semibold, FontWeight.SemiBold),
        Font(Res.font.outfit_thin, FontWeight.Thin)
    )
)

@Composable
fun CustomTypography() = Typography().run {

    val fontFamily = CustomFontFamily()
    copy(
        displayLarge = displayLarge.copy(fontFamily = fontFamily),
        displayMedium = displayMedium.copy(fontFamily = fontFamily),
        displaySmall = displaySmall.copy(fontFamily = fontFamily),
        headlineLarge = headlineLarge.copy(fontFamily = fontFamily),
        headlineMedium = headlineMedium.copy(fontFamily = fontFamily),
        headlineSmall = headlineSmall.copy(fontFamily = fontFamily),
        titleLarge = titleLarge.copy(fontFamily = fontFamily),
        titleMedium = titleMedium.copy(fontFamily = fontFamily),
        titleSmall = titleSmall.copy(fontFamily = fontFamily),
        bodyLarge = bodyLarge.copy(fontFamily =  fontFamily),
        bodyMedium = bodyMedium.copy(fontFamily = fontFamily),
        bodySmall = bodySmall.copy(fontFamily = fontFamily),
        labelLarge = labelLarge.copy(fontFamily = fontFamily),
        labelMedium = labelMedium.copy(fontFamily = fontFamily),
        labelSmall = labelSmall.copy(fontFamily = fontFamily)
    )
}



/**
 * Extensions defined based on Mantel
 * https://app.zeplin.io/project/6282afaa13f828115345e65b/styleguide/textstyles?tsid=629a43e6e75d04a2549dc1ab
 */
@Composable
fun Typography.headerXLarge(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 30.sp,
        lineHeight = 39.sp,
        letterSpacing = 0.45f.sp,
    )
}

@Composable
fun Typography.headerXLargeAlt(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        lineHeight = 39.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.headerLarge(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 31.2f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.headerLargeAlt(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp,
        lineHeight = 31.2f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.headerH1(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.headerH1Alt(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 26.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.headerH2(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 23.4f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.header_18_500_28(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontSize = 18.sp,
        fontWeight = FontWeight.W500,
        lineHeight = 28f.sp,
        fontStyle = FontStyle.Normal,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.body_14_600_20(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontSize = 14.sp,
        fontWeight = FontWeight.W600,
        lineHeight = 20f.sp,
        fontStyle = FontStyle.Normal,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodyLargeLight150(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.ExtraLight,
        fontSize = 18.sp,
        lineHeight = 27.sp,
        letterSpacing = 0.45f.sp
    )
}
@Composable
fun Typography.bodyLargeHeavy150(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 18.sp,
        lineHeight = 27.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodyMediumHeavy130(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.8f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodyMediumHeavy150(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodyMediumHeavy170(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 27.2f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodyMediumLight130(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.ExtraLight,
        fontSize = 16.sp,
        lineHeight = 20.8f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodyMediumLight150(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.ExtraLight,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodyMediumLight170(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.ExtraLight,
        fontSize = 16.sp,
        lineHeight = 27.2f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodySmallMediumHeavy130(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 15.sp,
        lineHeight = 19.8f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodySmallMediumLight130(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.ExtraLight,
        fontSize = 15.sp,
        lineHeight = 19.8f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodySmallHeavy130(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 18.2f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodySmallHeavy150(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodySmallHeavy170(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 23.8f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodySmallLight130(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.ExtraLight,
        fontSize = 14.sp,
        lineHeight = 18.2f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodySmallLight150(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.ExtraLight,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodySmallLight170(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.ExtraLight,
        fontSize = 14.sp,
        lineHeight = 23.8f.sp,
        letterSpacing = 0.45f.sp
    )
}


@Composable
fun Typography.bodyXSmallHeavy130(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 14.3f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodyXSmallHeavy150(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.5f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodyXSmallHeavy170(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 18.7f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodyXSmallLight130(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.ExtraLight,
        fontSize = 11.sp,
        lineHeight = 14.3f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodyXSmallLight150(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.ExtraLight,
        fontSize = 11.sp,
        lineHeight = 16.5.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.bodyXSmallLight170(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.ExtraLight,
        fontSize = 11.sp,
        lineHeight = 18.7f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.headerH3(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 20.8f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.headerH3Alt(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 20.8f.sp,
        letterSpacing = 0.45f.sp
    )
}


@Composable
fun Typography.headerH4(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 18.2f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.headerH4Alt(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 18.2f.sp,
        letterSpacing = 0.45f.sp
    )
}


@Composable
fun Typography.headerH5(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 15.6f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.headerH5Alt(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 15.6f.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.headerH6(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 13.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.headerH6Alt(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp,
        lineHeight = 13.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.tagsRegular(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 18.2.sp,
        letterSpacing = 0.45f.sp
    )
}

@Composable
fun Typography.tagsSmall(): TextStyle {
    return TextStyle(
        fontFamily = CustomFontFamily(),
        fontWeight = FontWeight.Medium,
        fontSize = 10.sp,
        lineHeight = 13.sp,
        letterSpacing = 0.45f.sp
    )
}

