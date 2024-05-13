package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import data.dao.UserDao
import data.entity.User


@Composable
fun Home(userDao: UserDao) {
    Text("Home")

    var userList by remember {  mutableStateOf(listOf<User>()) }

    LaunchedEffect(Unit) {
        userList = userDao.getAllUsers()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    ) {
        if (userList.isEmpty()){
            EmptyList()
        }else{
           List(userList)
        }
    }
}

@Composable
fun EmptyList(modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {

        Text(
            "Empty", style = TextStyle(
                fontSize = 49.sp,
                fontWeight = FontWeight.Bold
            ), modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun List(userList:List<User>) {
    LazyColumn {
        items(userList) {
            UserListItem(it)
        }
    }
}

@Composable
fun UserListItem(user:User) {
    Column(
        modifier = Modifier.fillMaxWidth()
            .background(color = Color.LightGray),

    ) {
       Text(text = "${user.name} ${user.surname}", style = TextStyle(
           fontSize = 18.sp
       )
       )
       Text(text = "id: ${user.id}", style = TextStyle(
           fontStyle = FontStyle.Italic,
           fontSize = 15.sp
       )
       )
    }

}