package com.example.easycareproject.ui.theme.screens

import MedicationReminderViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import java.time.LocalTime
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MedicationReminderScreen(
    navController: NavController,
    medicationReminderViewModel: MedicationReminderViewModel = viewModel()
) {
    var medication by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }
    val medicationList by medicationReminderViewModel.medicationList.observeAsState(emptyList())
    val hasReminders by medicationReminderViewModel.hasReminders.observeAsState(false)
    var showError by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF6F9FE)),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Medication Reminder",
                style = MaterialTheme.typography.headlineLarge.copy(color = Color(0xFF0D47A1)), // Dark blue
                modifier = Modifier.padding(bottom = 16.dp)
            )

            OutlinedTextField(
                value = medication,
                onValueChange = { medication = it },
                label = { Text("Medication Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary
                )
            )

            OutlinedTextField(
                value = time,
                onValueChange = { time = it },
                label = { Text("Reminder Time (HH:mm)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = { /* Handle keyboard action */ }),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    focusedBorderColor = MaterialTheme.colorScheme.primary
                )
            )

            Button(
                onClick = {
                    if (medication.isNotBlank() && time.isNotBlank()) {
                        try {
                            LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"))
                            medicationReminderViewModel.addReminder(medication, time)
                            medication = ""
                            time = ""
                            showError = false
                        } catch (e: Exception) {
                            showError = true
                        }
                    }
                },
                modifier = Modifier.align(Alignment.End),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Add Reminder", style = MaterialTheme.typography.bodyLarge.copy(color = Color.White))
            }

            if (showError) {
                Text(
                    text = "Invalid time format. Use HH:mm.",
                    color = Color.Red,
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = if (hasReminders) "You have a reminder" else "You don't have any reminder",
                    style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF0D47A1)),
                    modifier = Modifier.padding(16.dp)
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                medicationList.forEach { (med, reminderTime) ->
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        colors = CardDefaults.cardColors(containerColor = Color.White),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "$med at $reminderTime",
                                style = MaterialTheme.typography.bodyLarge.copy(color = Color(0xFF0D47A1))
                            )
                            IconButton(onClick = { medicationReminderViewModel.removeReminder(med, reminderTime) }) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = "Delete Reminder",
                                    tint = Color.Red
                                )
                            }
                        }
                    }
                }
            }

            TextButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = ButtonDefaults.textButtonColors(contentColor = MaterialTheme.colorScheme.primary)
            ) {
                Text("Go Back", style = MaterialTheme.typography.bodyLarge)
            }
        }
    }
}
