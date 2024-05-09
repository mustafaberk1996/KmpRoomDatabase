package di

import AppDatabase
import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase

actual class Factory {

    actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {
        val appContext = Application().applicationContext
        val dbFile = appContext.getDatabasePath("my_room.db")
        return Room.databaseBuilder<AppDatabase>(
            context = appContext,
            name = dbFile.absolutePath
        )
    }

}
