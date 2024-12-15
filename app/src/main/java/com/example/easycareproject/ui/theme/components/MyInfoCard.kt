package com.example.easycareproject.ui.theme.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.clickable
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.easycareproject.data.database.User

@Composable
fun MyInfoCard(navController: NavController, user: User) {
    val cardColor = if (user.username == "admin") Color.Cyan else Color.White

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(cardColor)
            .clickable {
                navController.navigate("user_info_screen")
            },
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Username: ${user.username}",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Password: ${user.password}",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}
