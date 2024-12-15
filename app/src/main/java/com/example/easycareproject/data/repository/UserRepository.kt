package com.example.easycareproject.data.repository

import com.example.easycareproject.data.database.User
import com.example.easycareproject.data.database.UserDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull


class UserRepository(private val userDao: UserDao) {

    // Flow to observe all users from the database
    val allUsers: Flow<List<User>> = userDao.getAllUsers()

    // Function to insert a new user into the database
    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    // Function to validate login credentials
    suspend fun login(username: String, password: String): User? {
        return userDao.getAllUsers()
            .firstOrNull()
            ?.find { it.username == username && it.password == password }
    }

    // Function to update user information in the database
    suspend fun updateUserInfo(userId: Int, doctorInfo: String, nearestPharmacy: String) {
        val user = userDao.getUserById(userId).firstOrNull()
        user?.let {
            val updatedUser = it.copy(doctorInfo = doctorInfo, nearestPharmacy = nearestPharmacy)
            userDao.updateUser(updatedUser)
        }
    }
}
