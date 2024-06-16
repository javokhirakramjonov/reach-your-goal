
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.bottomSheet.BottomSheetNavigator
import cafe.adriel.voyager.transitions.SlideTransition
import screen.tasks.TasksScreen
import theme.MainAppTheme
import theme.ThemeType

@Composable
fun App() {
    MainAppTheme(themeType = ThemeType.LIGHT) {
        BottomSheetNavigator(
            sheetBackgroundColor = Color.Transparent
        ) {
            Navigator(TasksScreen()) {
                SlideTransition(it)
            }
        }
    }
}