package org.example.project

sealed class MainNavigationGraph(val title: String) {
    data object UserList : MainNavigationGraph("UserList")
    data object AddUser : MainNavigationGraph("AddUser")
}