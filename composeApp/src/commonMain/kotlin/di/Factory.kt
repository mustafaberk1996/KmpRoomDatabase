package di

import AppDatabase
import androidx.room.RoomDatabase

expect class Factory {
    fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase>
}