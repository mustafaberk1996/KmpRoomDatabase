package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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
            color = MaterialTheme.colors.background,
        ) {
            MainScreen(database.build().userDao())
        }

    }
}

@Composable
fun MainScreen(userDao: UserDao) {
    Scaffold(
        topBar = {
            Row {
                Text("Add User", modifier = Modifier.fillMaxWidth(), style = MaterialTheme.typography.h1)
            }
        },

        ) {
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
