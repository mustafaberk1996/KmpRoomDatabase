package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.room.RoomDatabase
import data.Database
import kmproomdatabase.composeapp.generated.resources.Res
import kmproomdatabase.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App(database: RoomDatabase.Builder<Database>) {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background,
        ) {
            MainScreen()
        }

    }
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun MainScreen(){

    Scaffold(
        topBar = {
            Row {

                Image(
                    modifier = Modifier,
                    painter = painterResource(Res.drawable.compose_multiplatform),
                    contentDescription = ""
                )
                Text("Welcome Mustafa!")
            }

        },

    ){
        Column {
            var title by remember { mutableStateOf("") }
            var content by remember { mutableStateOf("") }

            TextField(value = title, onValueChange = { title = it }, modifier = Modifier.fillMaxWidth())
            TextField(value = content, onValueChange = { content = it }, modifier = Modifier.fillMaxWidth())
            Button(onClick = {

            }) {
                Text("Save")
            }
        }
    }


}


@Composable
fun MainScreenPreview(){
    MaterialTheme {
        MainScreen()
    }
}