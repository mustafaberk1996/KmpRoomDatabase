package org.example.project.user

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import data.dao.UserDao
import data.entity.User


@Composable
fun AddUserScreen(modifier: Modifier = Modifier, userDao: UserDao) {
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
    }
}

