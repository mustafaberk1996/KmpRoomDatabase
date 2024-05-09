import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.room.RoomDatabase
import data.Database
import data.dao.UserDao

@Composable
fun App(modifier: Modifier = Modifier, databaseBuilder:RoomDatabase.Builder<Database>) {

    MaterialTheme{

        val database = remember { databaseBuilder.build() }

        val userDao = remember { database.userDao() }


        MainContent(userDao)
    }

}


@Composable
fun MainContent(userDao: UserDao) {

}