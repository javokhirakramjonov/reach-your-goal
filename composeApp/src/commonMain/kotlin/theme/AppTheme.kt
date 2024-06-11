package theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val lightColorsScheme = lightColorScheme()
private val darkColorScheme = darkColorScheme()

private val typography = Typography()

@Composable
fun MainAppTheme(
    themeType: ThemeType = ThemeType.SYSTEM_DEFAULT,
    shouldAnimate: Boolean = false,
    content: @Composable() () -> Unit
) {
    val isDarkTheme = when (themeType) {
        ThemeType.SYSTEM_DEFAULT -> isSystemInDarkTheme()
        ThemeType.LIGHT -> false
        ThemeType.DARK -> true
    }

    MaterialTheme(
        colorScheme = if(isDarkTheme) darkColorScheme else lightColorsScheme,
        typography = typography,
        content = content
    )
}
