package com.example.easycareproject.ui.theme.screens

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.easycareproject.data.database.User


@Composable
fun MyInfoScreen(
    navController: NavController,
    user: User,
    onSave: (User) -> Unit
) {
    val name = remember { mutableStateOf(TextFieldValue(user.username)) }
    val password = remember { mutableStateOf(TextFieldValue(user.password)) }
    val doctorInfo = remember { mutableStateOf(TextFieldValue(user.doctorInfo)) }
    val nearestPharmacy = remember { mutableStateOf(TextFieldValue(user.nearestPharmacy)) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.Start
    ) {
        IconButton(onClick = { navController.popBackStack() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Title
        Text(
            text = "My Information",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        InputField(value = name.value, label = "Name", onValueChange = { name.value = it })
        Spacer(modifier = Modifier.height(8.dp))
        InputField(value = password.value, label = "Password", isPassword = true, onValueChange = { password.value = it })
        Spacer(modifier = Modifier.height(8.dp))
        InputField(value = doctorInfo.value, label = "Doctor Information", onValueChange = { doctorInfo.value = it })
        Spacer(modifier = Modifier.height(8.dp))
        InputField(value = nearestPharmacy.value, label = "Nearest Pharmacy", onValueChange = { nearestPharmacy.value = it })
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {
                val updatedUser = user.copy(
                    username = name.value.text,
                    password = password.value.text,
                    doctorInfo = doctorInfo.value.text,
                    nearestPharmacy = nearestPharmacy.value.text
                )
                onSave(updatedUser)
                navController.popBackStack()
            },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Text(text = "Save Changes", color = Color.White)
        }
    }
}

@Composable
fun InputField(
    value: TextFieldValue,
    label: String,
    isPassword: Boolean = false,
    onValueChange: (TextFieldValue) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth()
    )
}