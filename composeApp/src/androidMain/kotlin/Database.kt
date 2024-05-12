import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.Database
import kotlinx.coroutines.Dispatchers

fun getDatabase(context: Context): RoomDatabase.Builder<Database> {
    val dbFile = context.getDatabasePath("database.db")
    return Room.databaseBuilder<Database>(
        context = context.applicationContext,
        name = dbFile.absolutePath
    )
        .fallbackToDestructiveMigrationOnDowngrade(true)
        .setDriver(BundledSQLiteDriver()) // Very important
        .setQueryCoroutineContext(Dispatchers.IO)
}