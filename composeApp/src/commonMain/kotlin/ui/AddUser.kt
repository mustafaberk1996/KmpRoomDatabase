package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import data.dao.UserDao
import data.entity.User
import kmproomdatabase.composeapp.generated.resources.Res
import kmproomdatabase.composeapp.generated.resources.baseline_add_photo_alternate_24
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource


@OptIn(ExperimentalResourceApi::class)
@Composable
fun AddUser(userDao: UserDao) {
    Column(modifier = Modifier.padding(10.dp)) {

        var name by remember { mutableStateOf("") }
        var surname by remember { mutableStateOf("") }
        var clickedSave by remember { mutableStateOf(false) }



        if (clickedSave) {
            LaunchedEffect(Unit) {
                userDao.insert(User(name = name, surname = surname))
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

        Image(
            painter = painterResource(Res.drawable.baseline_add_photo_alternate_24),
            contentDescription = "",
            modifier = Modifier.clickable {
                //TODO open gallery and pick up a photo
            }
        )

        Button(
            onClick = {
                if (name.isBlank() || surname.isBlank()) return@Button
                clickedSave = true
            },
            modifier = Modifier.fillMaxWidth()

        ) {
            Text("Save")
        }

    }
}