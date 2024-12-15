package com.example.easycareproject.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val username: String,
    val password: String,
    var doctorInfo: String = "",
    var nearestPharmacy: String = ""
)
