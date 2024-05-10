package org.example.project

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import data.dao.UserDao
import data.entity.User
import getDatabase
import org.example.project.user.AddUserScreen
import org.example.project.user.UserListScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = getDatabase(this)
        setContent {
            MainScreen(
                userDao = database.build().userDao(),
                onClickAddButton = {}
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(userDao: UserDao, onClickAddButton: () -> Unit) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text("Users")
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
    ) { contentPadding ->
        Column(modifier = Modifier.padding(contentPadding)) {
            NavHost(navController = navController, startDestination = Screen.UserList.title) {
                composable(route = Screen.UserList.title) {
                    UserListScreen(userDao = userDao)
                }
                composable(route = Screen.AddUser.title) {
                    AddUserScreen(userDao = userDao)
                }
            }
        }
    }
}

