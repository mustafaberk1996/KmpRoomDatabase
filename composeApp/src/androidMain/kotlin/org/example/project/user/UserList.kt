package org.example.project.user

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import data.dao.UserDao
import data.entity.User


@Composable
fun UserListScreen(modifier: Modifier = Modifier, userDao: UserDao) {


    var userList by remember {  mutableStateOf(listOf<User>()) }

    LaunchedEffect(Unit) {
        userList = userDao.getAllUsers()
    }
    Column(modifier = Modifier.height(200.dp).fillMaxWidth().background(color = Color.LightGray)) {
        LazyColumn {
            items(userList) {
                UserListItem(user = it)
            }
        }
    }

}

@Composable
fun UserListItem(modifier: Modifier = Modifier, user:User) {
    Column(
        modifier = Modifier.border(
            width = 1.dp,
            color = Color.Cyan,
            shape = RoundedCornerShape(18.dp)
        )
    ) {
        Text(user.name, style = MaterialTheme.typography.titleLarge)
        Text(user.name, style = MaterialTheme.typography.titleSmall)
    }
}