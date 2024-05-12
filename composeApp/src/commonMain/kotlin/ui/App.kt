package ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.room.RoomDatabase
import data.Database
import data.dao.UserDao
import kmproomdatabase.composeapp.generated.resources.Res
import kmproomdatabase.composeapp.generated.resources.app_name
import kmproomdatabase.composeapp.generated.resources.baseline_home_filled_24
import kmproomdatabase.composeapp.generated.resources.baseline_person_add_24
import kmproomdatabase.composeapp.generated.resources.dedeeee
import kmproomdatabase.composeapp.generated.resources.room
import kmproomdatabase.composeapp.generated.resources.window
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App(database: RoomDatabase.Builder<Database>) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            MainScreen(database.build().userDao())
        }

    }
}

sealed class Screen @OptIn(ExperimentalResourceApi::class) constructor(val route: String, val label: String, val icon: DrawableResource) {
    @OptIn(ExperimentalResourceApi::class)
    data object Home : Screen("home", "Home", Res.drawable.baseline_home_filled_24)
    @OptIn(ExperimentalResourceApi::class)
    data object Plus : Screen("plus", "Plus", Res.drawable.baseline_person_add_24)
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CustomBottomNavigation(
    navController: NavController,
    items: List<Screen>
) {
    BottomNavigation {
        val currentDestination = navController.currentBackStackEntryAsState()
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(screen.icon),
                        contentDescription = screen.label,
                        modifier = Modifier.size(26.dp)
                    )
                },
                label = { Text(screen.label) },
                selected = currentDestination.value?.destination?.route == screen.route,
                onClick = {
                    navController.navigate(screen.route)
                }
            )
        }
    }
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun TopBar(modifier: Modifier = Modifier) {
    Row {
        Text(
            text = stringResource(Res.string.dedeeee),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.h3
        )
    }
}



@Composable
fun MainScreen(userDao: UserDao) {

    val navController = rememberNavController()
    val items = listOf(Screen.Home, Screen.Plus)

    Scaffold(
        bottomBar = {
            CustomBottomNavigation(navController, items)
        },
        topBar = {
          TopBar()
        },
    ) {

        NavHost(navController = navController, startDestination = Screen.Home.route) {
            composable(Screen.Home.route) {
                Home(userDao)
            }
            composable(Screen.Plus.route) {
                Plus(userDao)
            }
        }
    }
}
