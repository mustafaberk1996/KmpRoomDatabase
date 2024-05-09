package data

import androidx.room.Database
import androidx.room.RoomDatabase
import data.dao.UserDao
import data.entity.User

@Database(entities = [User::class], version = 1)
abstract class Database: RoomDatabase() {
    abstract fun userDao(): UserDao

}