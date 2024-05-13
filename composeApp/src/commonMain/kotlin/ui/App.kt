package ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Surface
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
import kmproomdatabase.composeapp.generated.resources.title_add_user
import kmproomdatabase.composeapp.generated.resources.title_home
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Composable
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


sealed class Screen(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Home : Screen("home", "Home", Icons.Default.Home)
    data object AddUser : Screen("addUser", "Add User", Icons.Default.Add)
}

@Composable
fun CustomBottomNavigation(
    navController: NavController,
    items: List<Screen>,
    bottomNavigationClicked:(screen: Screen)->Unit
) {
    NavigationBar {
        val currentDestination = navController.currentBackStackEntryAsState()
        items.forEach { screen ->
            NavigationBarItem(
                icon = {
                    Icon(imageVector =  screen.icon, contentDescription = "")
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
fun TopBar(titleRes:StringResource) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth().padding(10.dp)
    ) {
        Text(
            text = stringResource(titleRes),
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
    var titleResource by remember { mutableStateOf(Res.string.title_home) }

    navController.addOnDestinationChangedListener { controller, destination, arguments ->
        titleResource = when(destination.route){
               Screen.Home.route-> Res.string.title_home
            else -> Res.string.title_add_user
        }
    }

    Scaffold(
        bottomBar = {
            CustomBottomNavigation(navController, items){screen: Screen ->
                navController.navigate(screen.route)
            }
        },
        topBar = {
            TopBar(titleResource)
        },
    ) {contentPadding->
        NavHost(navController = navController, startDestination = Screen.Home.route, modifier = Modifier.padding(contentPadding)) {
            composable(Screen.Home.route) {
                Home(userDao)
            }
            composable(Screen.AddUser.route) {
                AddUser(userDao)
            }
        }
    }
}
