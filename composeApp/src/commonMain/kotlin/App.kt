
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import screen.home.HomeScreen
import theme.MainAppTheme
import theme.ThemeType

@Composable
fun App() {   MainAppTheme(themeType = ThemeType.DARK) {
        Navigator(HomeScreen()) {
            SlideTransition(it)
        }
    }
}