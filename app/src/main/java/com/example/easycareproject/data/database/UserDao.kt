package com.example.easycareproject.data.database

import androidx.room.*
import com.example.easycareproject.data.database.User
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user_table ORDER BY username ASC")
    fun getAllUsers(): Flow<List<User>>

    @Query("DELETE FROM user_table WHERE id = :userId")
    suspend fun deleteUserById(userId: Int)

    @Query("SELECT * FROM user_table WHERE username = :username AND password = :password")
    suspend fun login(username: String, password: String): User?

    @Query("UPDATE user_table SET doctorInfo = :doctorInfo, nearestPharmacy = :nearestPharmacy WHERE id = :userId")
    suspend fun updateUserInfo(userId: Int, doctorInfo: String, nearestPharmacy: String)

    @Query("SELECT * FROM user_table WHERE id = :userId")
    fun getUserById(userId: Int): Flow<User?>

    @Update
    suspend fun updateUser(user: User)
}
