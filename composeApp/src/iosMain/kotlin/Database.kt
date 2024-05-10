import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import data.Database
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import platform.Foundation.NSHomeDirectory // This shall too in Windows.

//fun getDatabase(): RoomDatabase.Builder<Database> {
//    val dbFile = NSHomeDirectory() + "/user.db"
//    return Room.databaseBuilder<Database>(
//        name = dbFile,
//        factory = { Database::class.instantiateImpl() } // This too will show error
//    )
//        .fallbackToDestructiveMigrationOnDowngrade(true)
//        .setDriver(BundledSQLiteDriver()) // Very important
//        .setQueryCoroutineContext(Dispatchers.IO)
//}