package theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val lightColorsScheme = lightColorScheme()
private val darkColorScheme = darkColorScheme()

private val typography = Typography()

@Composable
fun MainAppTheme(
    themeType: ThemeType = ThemeType.SYSTEM_DEFAULT,
    shouldAnimate: Boolean = true,
    content: @Composable() () -> Unit
) {
    val isDarkTheme = when (themeType) {
        ThemeType.SYSTEM_DEFAULT -> isSystemInDarkTheme()
        ThemeType.LIGHT -> false
        ThemeType.DARK -> true
    }

    val colorScheme = if (isDarkTheme) darkColorScheme else lightColorsScheme

    MaterialTheme(
        colorScheme = colorScheme.let { if (shouldAnimate) it.animatedSwitch() else it },
        typography = typography,
        content = content
    )
}

@Composable
private fun ColorScheme.animatedSwitch() = copy(
    primary = animateColor(primary),
    onPrimary = animateColor(onPrimary),
    primaryContainer = animateColor(primaryContainer),
    onPrimaryContainer = animateColor(onPrimaryContainer),
    inversePrimary = animateColor(inversePrimary),
    secondary = animateColor(secondary),
    onSecondary = animateColor(onSecondary),
    secondaryContainer = animateColor(secondaryContainer),
    onSecondaryContainer = animateColor(onSecondaryContainer),
    tertiary = animateColor(tertiary),
    onTertiary = animateColor(onTertiary),
    tertiaryContainer = animateColor(tertiaryContainer),
    onTertiaryContainer = animateColor(onTertiaryContainer),
    background = animateColor(background),
    onBackground = animateColor(onBackground),
    surface = animateColor(surface),
    onSurface = animateColor(onSurface),
    surfaceVariant = animateColor(surfaceVariant),
    onSurfaceVariant = animateColor(onSurfaceVariant),
    surfaceTint = animateColor(surfaceTint),
    inverseSurface = animateColor(inverseSurface),
    inverseOnSurface = animateColor(inverseOnSurface),
    error = animateColor(error),
    onError = animateColor(onError),
    errorContainer = animateColor(errorContainer),
    onErrorContainer = animateColor(onErrorContainer),
    outline = animateColor(outline),
    outlineVariant = animateColor(outlineVariant),
    scrim = animateColor(scrim),
    surfaceBright = animateColor(surfaceBright),
    surfaceDim = animateColor(surfaceDim),
    surfaceContainer = animateColor(surfaceContainer),
    surfaceContainerHigh = animateColor(surfaceContainerHigh),
    surfaceContainerHighest = animateColor(surfaceContainerHighest),
    surfaceContainerLow = animateColor(surfaceContainerLow),
    surfaceContainerLowest = animateColor(surfaceContainerLowest),
)

@Composable
private fun animateColor(
    color: Color,
) = animateColorAsState(
    targetValue = color,
    animationSpec = tween(200)
).value