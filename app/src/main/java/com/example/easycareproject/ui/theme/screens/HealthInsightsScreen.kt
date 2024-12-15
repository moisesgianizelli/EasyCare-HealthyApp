package com.example.easycareproject.ui.theme.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.easycareproject.ui.theme.viewmodel.HealthInsightsViewModel

@Composable
fun HealthInsightsScreen(
    navController: NavController,
    healthInsightsViewModel: HealthInsightsViewModel
) {
    //val healthInsightsViewModel: HealthInsightsViewModel = viewModel()
    var inputValue by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "Update Health Insights",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        OutlinedTextField(
            value = inputValue,
            onValueChange = {
                inputValue = it
                errorMessage = ""
            },
            label = { Text("Enter new insight value") },
            isError = errorMessage.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        )

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Button(
            onClick = {
                val newValue = inputValue.toIntOrNull()
                if (newValue != null) {
                    healthInsightsViewModel.updateInsightValue(newValue)
                    navController.popBackStack()
                } else {
                    errorMessage = "Please enter a valid number."
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Update and Return")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Press the button to save and go back",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
    }
}
