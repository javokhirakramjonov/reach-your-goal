
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import presentation.screen.dashboard.DashboardScreen
import theme.MainAppTheme
import theme.ThemeType

@Composable
fun App() {
    MainAppTheme(themeType = ThemeType.SYSTEM_DEFAULT) {
        BottomSheetNavigator(
            sheetBackgroundColor = Color.Transparent
        ) {
            Navigator(DashboardScreen()) {
                SlideTransition(it)
            }
        }
    }
}