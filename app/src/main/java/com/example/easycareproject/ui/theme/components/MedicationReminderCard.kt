package com.example.easycareproject.ui.theme.components

import MedicationReminderViewModel
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun MedicationReminderCard(
    navController: NavController,
    viewModel: MedicationReminderViewModel
) {
    val hasReminders by viewModel.hasReminders.observeAsState(initial = false)
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("medication_reminder_screen")
            },
        shape = MaterialTheme.shapes.medium.copy(CornerSize(12.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Alarm,
                contentDescription = "Medication Reminder",
                modifier = Modifier.size(40.dp),
                tint = Color.Blue
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                Text(
                    text = "Manage your medication",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onPrimaryContainer
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    androidx.compose.material3.Button(
                        onClick = {
                            navController.navigate("medication_reminder_screen")
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1.2f),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(text = "Reminder")
                    }

                    androidx.compose.material3.Button(
                        onClick = {
                            navController.navigate("timer_screen")
                        },
                        modifier = Modifier
                            .padding(8.dp)
                            .weight(1f),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Text(text = "Timer")
                    }
                }
            }
        }
    }
}

