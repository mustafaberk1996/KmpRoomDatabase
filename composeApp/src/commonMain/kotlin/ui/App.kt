package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.room.RoomDatabase
import data.Database
import data.dao.UserDao
import data.entity.User
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App(database: RoomDatabase.Builder<Database>) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainScreen(
                userDao = database.build().userDao(),
                onClickAddButton = {}
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(userDao: UserDao, onClickAddButton:()-> Unit) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Fantasy Premier League")
                },
                //scrollBehavior = scrollBehavior,
                actions = {
                    IconButton(onClick = {
                        onClickAddButton()
                    }) {
                        Icon(Icons.Filled.Add, contentDescription = "Add")
                    }
                }
            )
        },
        ) {
        Column {
            NavHost(navController = navController, startDestination = ""){
                //TODO I was here
            }

        }

        Column {
            var name by remember { mutableStateOf("") }
            var clickedSave by remember { mutableStateOf(false) }

            if (clickedSave) {
                LaunchedEffect(Unit) {
                    userDao.insert(User(name = name))
                    clickedSave = false
                }
            }
            TextField(
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth()
            )
            Button(onClick = {
                clickedSave = true
            }) {
                Text("Save")
            }

            var userList by remember {  mutableStateOf(listOf<User>()) }

            LaunchedEffect(Unit) {
                userList = userDao.getAllUsers()
            }
            Column(modifier = Modifier.height(200.dp).fillMaxWidth().background(color = Color.LightGray)) {
                LazyColumn {
                    items(userList) {
                        Text("User: ${it.name}")
                    }
                }
            }
        }
    }
}

