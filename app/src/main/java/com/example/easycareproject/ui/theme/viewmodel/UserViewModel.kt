package com.example.easycareproject.ui.theme.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.easycareproject.data.database.User
import com.example.easycareproject.data.repository.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.launch


class UserViewModel(private val repository: UserRepository) : ViewModel() {

    var loggedInUser: User? = null
        private set

    // Login functionality
    fun login(username: String, password: String, onSuccess: () -> Unit, onError: (String) -> Unit) = viewModelScope.launch {
        val user = repository.login(username, password)
        if (user != null) {
            loggedInUser = user
            onSuccess()
        } else {
            onError("Invalid credentials")
        }
    }

    // Flow to observe all users
    val allUsers: Flow<List<User>> = repository.allUsers

    // Insert a new user (Sign-Up)
    fun insertUser(user: User, onSuccess: () -> Unit, onError: (String) -> Unit) = viewModelScope.launch {
        val existingUser = repository.allUsers.firstOrNull()?.find { it.username == user.username }
        if (existingUser != null) {
            onError("Username already exists.")
        } else {
            repository.insertUser(user)
            onSuccess()
        }
    }

    // Login functionality
    fun login(username: String, password: String, onSuccess: (User?) -> Unit) = viewModelScope.launch {
        val user = repository.login(username, password)
        onSuccess(user)
    }

    // Update user information
    fun updateUserInfo(userId: Int, doctorInfo: String, nearestPharmacy: String) = viewModelScope.launch {
        repository.updateUserInfo(userId, doctorInfo, nearestPharmacy)
    }
}
