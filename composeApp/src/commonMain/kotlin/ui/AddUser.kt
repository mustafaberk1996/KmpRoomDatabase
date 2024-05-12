package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import data.dao.UserDao
import data.entity.User


@Composable
fun AddUser(userDao: UserDao) {
    Column(modifier = Modifier.padding(10.dp)) {

        var name by remember { mutableStateOf("") }
        var surname by remember { mutableStateOf("") }
        var clickedSave by remember { mutableStateOf(false) }



        if (clickedSave) {
            LaunchedEffect(Unit) {
                userDao.insert(User(name = name))
                name = ""
                surname = ""
                clickedSave = false
            }
        }


        TextField(
            value = name,
            placeholder = {
                Text("name")
            },
            onValueChange = { name = it },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            placeholder = {
                Text("surname")
            },
            value = surname,
            onValueChange = { surname = it },
            modifier = Modifier.fillMaxWidth()
        )
        Button(
            onClick = {
            clickedSave = true
        },
            modifier = Modifier.fillMaxWidth()

        ) {
            Text("Save")
        }

    }
}