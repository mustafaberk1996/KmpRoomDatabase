package data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import data.entity.User

@Dao
interface UserDao {

    @Upsert
    suspend fun insert(user: User): Long

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Int): User?

    @Delete
    suspend fun delete(user: User)

    @Query("DELETE FROM user WHERE id = :id")
    suspend fun deleteById(id: Int): Int

    @Query("SELECT * FROM user")
    suspend fun getAllUsers(): List<User>
}