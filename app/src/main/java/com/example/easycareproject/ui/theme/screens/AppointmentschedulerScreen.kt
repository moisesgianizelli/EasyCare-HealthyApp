package com.example.easycareproject.ui.theme.screens

import AppointmentViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.navigation.NavController
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun AppointmentSchedulerScreen(
    navController: NavController,
    appointmentViewModel: AppointmentViewModel = viewModel()
) {
    var appointmentText by remember { mutableStateOf("") }
    val appointmentList by appointmentViewModel.appointmentList.observeAsState(emptyList()) // Observar a lista de compromissos

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), // Garante que a coluna preencha toda a largura
        verticalArrangement = Arrangement.spacedBy(16.dp) // espaçamento controlado entre os elementos
    ) {
        // Título da tela
        Text(
            text = "Appointment Scheduler",
            style = MaterialTheme.typography.headlineLarge,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Text(
            text = "Manage your appointments below:",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo de entrada para compromissos
        BasicTextField(
            value = appointmentText,
            onValueChange = { appointmentText = it },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray, RoundedCornerShape(8.dp))
                .padding(horizontal = 16.dp, vertical = 8.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart
                ) {
                    if (appointmentText.isEmpty()) {
                        Text(
                            text = "Enter appointment details",
                            style = MaterialTheme.typography.bodyMedium.copy(color = Color.Gray)
                        )
                    }
                    innerTextField()
                }
            }
        )
        Button(
            onClick = {
                if (appointmentText.isNotBlank() && appointmentList.size < 5) {
                    appointmentViewModel.addAppointment(appointmentText)
                    appointmentText = ""
                }
            },
            modifier = Modifier.align(Alignment.End),
            enabled = appointmentList.size < 5
        ) {
            Text(
                text = if (appointmentList.size < 5) "Add Appointment" else "Limit Reached"
            )
        }

        if (appointmentList.isEmpty()) {
            Text(
                text = "No appointments yet.",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray,
                modifier = Modifier.padding(16.dp)
            )
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                appointmentList.forEachIndexed { index, appointment ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Text(
                            text = "Appointment ${index + 1}: $appointment",
                            style = MaterialTheme.typography.bodyLarge,
                            modifier = Modifier.padding(16.dp)
                        )
                    }
                }
            }
        }

        Button(
            onClick = { navController.popBackStack() },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Go Back")
        }
    }
}

