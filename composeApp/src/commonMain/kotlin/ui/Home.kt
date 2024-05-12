package ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

    Text("Empty", style = TextStyle(
        fontSize = 49.sp,
        fontWeight = FontWeight.Bold
    ), modifier = Modifier.align(Alignment.CenterHorizontally)
    )
    }
}

@Composable
fun List(userList:List<User>) {
    LazyColumn {
        items(userList) {
            Text("User: ${it.name}")
        }
    }
}