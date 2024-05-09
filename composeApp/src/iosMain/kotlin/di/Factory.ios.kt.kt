package di

import AppDatabase
import androidx.room.Room
import androidx.room.RoomDatabase
import platform.Foundation.NSHomeDirectory


actual class Factory {

    actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        val dbFilePath = NSHomeDirectory() + "/my_room.db"
        return Room.databaseBuilder<AppDatabase>(
            name = dbFilePath,
            factory = { AppDatabase::class.instantiateImpl() }
        )
    }
}
