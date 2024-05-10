package org.example.project

sealed class Screen(val title: String) {
    data object UserList : Screen("UserList")
    data object AddUser : Screen("AddUser")
}