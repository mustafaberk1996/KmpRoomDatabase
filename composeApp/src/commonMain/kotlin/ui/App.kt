package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
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
import kmproomdatabase.composeapp.generated.resources.baseline_home_filled_24
import kmproomdatabase.composeapp.generated.resources.baseline_person_add_24
import kmproomdatabase.composeapp.generated.resources.title_add_user
import kmproomdatabase.composeapp.generated.resources.title_home
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App(database: RoomDatabase.Builder<Database>) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            MainScreen(database.build().userDao())
        }

    }
}

sealed class Screen @OptIn(ExperimentalResourceApi::class) constructor(
    val route: String,
    val label: String,
    val icon: DrawableResource,
    val title: StringResource
) {
    @OptIn(ExperimentalResourceApi::class)
    data object Home : Screen("home", "Home", Res.drawable.baseline_home_filled_24, Res.string.title_home)
    @OptIn(ExperimentalResourceApi::class)
    data object AddUser : Screen("addUser", "Add User", Res.drawable.baseline_person_add_24, Res.string.title_add_user)
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun CustomBottomNavigation(
    navController: NavController,
    items: List<Screen>,
    bottomNavigationClicked:(screen:Screen)->Unit
) {
    NavigationBar {
        val currentDestination = navController.currentBackStackEntryAsState()
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(screen.icon),
                        contentDescription = screen.label
                    )
                },
                label = { Text(screen.label) },
                selected = currentDestination.value?.destination?.route == screen.route,
                onClick = {
                    bottomNavigationClicked(screen)
                }
            )
        }
    }
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun TopBar(title:StringResource) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    ) {
        Text(
            text = stringResource(title),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleLarge
        )
    }
}



@OptIn(ExperimentalResourceApi::class)
@Composable
fun MainScreen(userDao: UserDao) {


    val navController = rememberNavController()
    val items = listOf(Screen.Home, Screen.AddUser)
    var title by remember { mutableStateOf(Res.string.title_home) }


    Scaffold(
        bottomBar = {
            CustomBottomNavigation(navController, items){screen: Screen ->
                title = screen.title
                navController.navigate(screen.route)
            }
        },
        topBar = {
          TopBar(title)
        },
    ) {

        NavHost(navController = navController, startDestination = Screen.Home.route) {
            composable(Screen.Home.route) {
                Home(userDao)
            }
            composable(Screen.AddUser.route) {
                AddUser(userDao)
            }
        }
    }
}
