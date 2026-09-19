package com.swordfish.lemuroid.app.mobile.shared.compose.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

/**
 * Nemuroid app theme — dynamic color (Material You) is intentionally disabled.
 * Supports multiple design systems with Dark and Light mode reactivity.
 */

data class AppThemeSettings(
    val themeType: AppThemeType = AppThemeType.NOTHING
) {
    val isGruvbox get() = themeType == AppThemeType.GRUVBOX
}

enum class AppThemeType {
    NOTHING,
    GRUVBOX,
    CATPPUCCIN,
    NORD,
    TOKYO_NIGHT;

    companion object {
        fun fromString(value: String?): AppThemeType {
            return entries.find { it.name.lowercase() == value?.lowercase() } ?: NOTHING
        }
    }
}

val LocalAppThemeSettings = staticCompositionLocalOf { AppThemeSettings() }

// --- Nothing OS Color Schemes ---

private fun getNothingDarkColorScheme(primaryColor: Color) =
    darkColorScheme(
        primary = primaryColor,
        onPrimary = Color.White,
        primaryContainer = primaryColor.copy(alpha = 0.7f),
        onPrimaryContainer = Color.White,
        secondary = AppDockBackground,
        onSecondary = Color.White,
        secondaryContainer = AppDockBackground.copy(alpha = 0.7f),
        onSecondaryContainer = Color.White,
        background = Color.Black,
        onBackground = Color.White,
        surface = Color.Black,
        onSurface = Color.White,
        surfaceVariant = AppCardBackground,
        onSurfaceVariant = Color.White,
        outline = AppDockBackground,
        error = Color(0xFFFF3B30),
    )

private fun getNothingLightColorScheme(primaryColor: Color) =
    lightColorScheme(
        primary = primaryColor,
        onPrimary = Color.Black,
        primaryContainer = primaryColor.copy(alpha = 0.7f),
        onPrimaryContainer = Color.Black,
        secondary = AppDockBackgroundLight,
        onSecondary = Color.Black,
        secondaryContainer = AppDockBackgroundLight.copy(alpha = 0.7f),
        onSecondaryContainer = Color.Black,
        background = Color.White,
        onBackground = Color.Black,
        surface = Color.White,
        onSurface = Color.Black,
        surfaceVariant = Color.White,
        onSurfaceVariant = Color.Black,
        outline = AppDockBackgroundLight,
        error = Color(0xFFFF3B30),
    )

// --- Gruvbox Color Schemes ---

private fun getGruvboxDarkColorScheme() =
    darkColorScheme(
        primary = GruvboxRed,
        onPrimary = GruvboxFg0,
        primaryContainer = GruvboxRed.copy(alpha = 0.7f),
        onPrimaryContainer = GruvboxFg0,
        secondary = GruvboxBg1,
        onSecondary = GruvboxFg0,
        secondaryContainer = GruvboxBg1.copy(alpha = 0.7f),
        onSecondaryContainer = GruvboxFg0,
        background = GruvboxBg0,
        onBackground = GruvboxFg0,
        surface = GruvboxBg0,
        onSurface = GruvboxFg0,
        surfaceVariant = GruvboxBg1,
        onSurfaceVariant = GruvboxFg1,
        outline = GruvboxGray,
        error = GruvboxRed,
    )

private fun getGruvboxLightColorScheme() =
    lightColorScheme(
        primary = GruvboxRed,
        onPrimary = GruvboxBg0,
        primaryContainer = GruvboxRed.copy(alpha = 0.7f),
        onPrimaryContainer = GruvboxBg0,
        secondary = GruvboxBg1Light,
        onSecondary = GruvboxFg0Light,
        secondaryContainer = GruvboxBg1Light.copy(alpha = 0.7f),
        onSecondaryContainer = GruvboxFg0Light,
        background = GruvboxBg0Light,
        onBackground = GruvboxFg0Light,
        surface = GruvboxBg0Light,
        onSurface = GruvboxFg0Light,
        surfaceVariant = GruvboxBg1Light,
        onSurfaceVariant = GruvboxFg1Light,
        outline = GruvboxGray,
        error = GruvboxRed,
    )

// --- Catppuccin Color Schemes ---

private fun getCatppuccinDarkColorScheme() =
    darkColorScheme(
        primary = CatppuccinMochaMauve,
        onPrimary = CatppuccinMochaBase,
        primaryContainer = CatppuccinMochaMauve.copy(alpha = 0.7f),
        onPrimaryContainer = CatppuccinMochaBase,
        secondary = CatppuccinMochaSurface0,
        onSecondary = CatppuccinMochaText,
        secondaryContainer = CatppuccinMochaSurface0.copy(alpha = 0.7f),
        onSecondaryContainer = CatppuccinMochaText,
        background = CatppuccinMochaBase,
        onBackground = CatppuccinMochaText,
        surface = CatppuccinMochaBase,
        onSurface = CatppuccinMochaText,
        surfaceVariant = CatppuccinMochaSurface0,
        onSurfaceVariant = CatppuccinMochaSubtext0,
        outline = CatppuccinMochaBlue,
        error = CatppuccinMochaRed,
    )

private fun getCatppuccinLightColorScheme() =
    lightColorScheme(
        primary = Color(0xFF8839EF), // Latte Mauve
        onPrimary = CatppuccinLatteBase,
        primaryContainer = Color(0xFF8839EF).copy(alpha = 0.7f),
        onPrimaryContainer = CatppuccinLatteBase,
        secondary = CatppuccinLatteSurface0,
        onSecondary = CatppuccinLatteText,
        secondaryContainer = CatppuccinLatteSurface0.copy(alpha = 0.7f),
        onSecondaryContainer = CatppuccinLatteText,
        background = CatppuccinLatteBase,
        onBackground = CatppuccinLatteText,
        surface = CatppuccinLatteBase,
        onSurface = CatppuccinLatteText,
        surfaceVariant = CatppuccinLatteSurface0,
        onSurfaceVariant = CatppuccinLatteSubtext0,
        outline = Color(0xFF1E66F5), // Latte Blue
        error = Color(0xFFD20F39), // Latte Red
    )

// --- Nord Color Scheme ---

private fun getNordDarkColorScheme() =
    darkColorScheme(
        primary = NordBlue,
        onPrimary = NordBg0,
        primaryContainer = NordBlue.copy(alpha = 0.7f),
        onPrimaryContainer = NordBg0,
        secondary = NordBg1,
        onSecondary = NordFg0,
        secondaryContainer = NordBg1.copy(alpha = 0.7f),
        onSecondaryContainer = NordFg0,
        background = NordBg0,
        onBackground = NordFg0,
        surface = NordBg0,
        onSurface = NordFg0,
        surfaceVariant = NordBg1,
        onSurfaceVariant = NordFg1,
        outline = NordAqua,
        error = NordRed,
    )

// --- Tokyo Night Color Scheme ---

private fun getTokyoNightDarkColorScheme() =
    darkColorScheme(
        primary = TokyoNightBlue,
        onPrimary = TokyoNightBlack,
        primaryContainer = TokyoNightBlue.copy(alpha = 0.7f),
        onPrimaryContainer = TokyoNightBlack,
        secondary = TokyoNightGray,
        onSecondary = TokyoNightFg,
        secondaryContainer = TokyoNightGray.copy(alpha = 0.7f),
        onSecondaryContainer = TokyoNightFg,
        background = TokyoNightBg,
        onBackground = TokyoNightFg,
        surface = TokyoNightBg,
        onSurface = TokyoNightFg,
        surfaceVariant = TokyoNightBlack,
        onSurfaceVariant = TokyoNightWhite,
        outline = TokyoNightCyan,
        error = TokyoNightRed,
    )

@Composable
fun AppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    themeType: AppThemeType = AppThemeType.NOTHING,
    useSurface: Boolean = true,
    primaryColor: Color = AppPrimary,
    content: @Composable () -> Unit,
) {
    val colors = when (themeType) {
        AppThemeType.GRUVBOX -> if (darkTheme) getGruvboxDarkColorScheme() else getGruvboxLightColorScheme()
        AppThemeType.CATPPUCCIN -> if (darkTheme) getCatppuccinDarkColorScheme() else getCatppuccinLightColorScheme()
        AppThemeType.NORD -> getNordDarkColorScheme() // Nord is typically used as a dark theme
        AppThemeType.TOKYO_NIGHT -> getTokyoNightDarkColorScheme() // Tokyo Night is typically dark
        AppThemeType.NOTHING -> if (darkTheme) getNothingDarkColorScheme(primaryColor) else getNothingLightColorScheme(primaryColor)
    }

    CompositionLocalProvider(LocalAppThemeSettings provides AppThemeSettings(themeType = themeType)) {
        MaterialTheme(colorScheme = colors) {
            if (useSurface) {
                Surface(color = MaterialTheme.colorScheme.background) {
                    content()
                }
            } else {
                content()
            }
        }
    }
}
