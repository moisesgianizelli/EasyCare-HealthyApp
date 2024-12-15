package com.example.easycareproject.ui.theme.screens

import AppointmentViewModel
import EmergencyButton
import MedicationReminderViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.easycareproject.data.database.User
import com.example.easycareproject.ui.theme.components.*
import com.example.easycareproject.ui.theme.viewmodel.DailyTipsViewModel
import com.example.easycareproject.ui.theme.viewmodel.HealthInsightsViewModel

@Composable
fun DashboardScreen(
    navController: NavController,
    dailyTipsViewModel: DailyTipsViewModel = viewModel(),
    healthInsightsViewModel: HealthInsightsViewModel = viewModel(),
    appointmentViewModel: AppointmentViewModel = viewModel(),
    medicationReminderViewModel: MedicationReminderViewModel = viewModel(),
    user: User
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color(0xFFBBDEFB), Color(0xFF64B5F6))
                )
            )
            .padding(16.dp)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Text(
                    text = "EasyCare Dashboard",
                    style = MaterialTheme.typography.headlineLarge.copy(
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    ),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
            }
            item {
                DashboardCard(
                    title = "My Info",
                    description = "View your profile and account details",
                    icon = Icons.Default.Person,
                    backgroundColor = Color(0xFF4CAF50),
                    onClick = { MyInfoCard(navController = navController, user = user) }
                )
            }

            item {
                DashboardCard(
                    title = "Delivery Requests",
                    description = "Manage your deliveries",
                    icon = Icons.Default.LocalShipping,
                    backgroundColor = Color(0xFFFF9800),
                    onClick = {
                        DeliveryRequestCard(navController = navController) { isRequested ->
                            println("Delivery status: $isRequested")
                        }
                    }
                )
            }

            item {
                DashboardCard(
                    title = "Medication Reminder",
                    description = "Set and manage your reminders",
                    icon = Icons.Default.Medication,
                    backgroundColor = Color(0xFFE91E63),
                    onClick = { MedicationReminderCard(navController = navController, viewModel = medicationReminderViewModel) }
                )
            }

            item {
                DashboardCard(
                    title = "Appointments",
                    description = "Schedule and track appointments",
                    icon = Icons.Default.CalendarToday,
                    backgroundColor = Color(0xFF3F51B5),
                    onClick = { AppointmentSchedulerCard(navController = navController, viewModel = appointmentViewModel) }
                )
            }

            item {
                DashboardCard(
                    title = "Health Insights",
                    description = "Track your health data",
                    icon = Icons.Default.Favorite,
                    backgroundColor = Color(0xFF009688),
                    onClick = {
                        HealthInsightsCard(
                            navController = navController,
                            viewModel = healthInsightsViewModel
                        )
                    }
                )
            }

            item {
                DashboardCard(
                    title = "Daily Tips",
                    description = "Achieve your goals with tips",
                    icon = Icons.Default.TipsAndUpdates,
                    backgroundColor = Color(0xFF673AB7),
                    onClick = {
                        DailyTipsCard(
                            navController = navController,
                            viewModel = dailyTipsViewModel
                        )
                    }
                )
            }
        }
        Row(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            EmergencyButton(onClick = { println("Emergency button clicked!") })
            TorchButton()
        }
    }
}

@Composable
fun DashboardCard(
    title: String,
    description: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    backgroundColor: Color,
    onClick: @Composable () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
                Spacer(modifier = Modifier.width(16.dp))
                Column {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = description,
                        style = MaterialTheme.typography.bodyMedium.copy(color = Color.White)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            onClick()
        }
    }
}

@Composable
fun TorchButton() {
    val context = LocalContext.current

    Button(
        onClick = {
            println("Torch button clicked!")
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.DarkGray,
            contentColor = Color.White
        ),
        shape = androidx.compose.foundation.shape.CircleShape,
        modifier = Modifier.size(64.dp)
    ) {
        Icon(
            imageVector = Icons.Default.FlashlightOn,
            contentDescription = "Torch Button",
            modifier = Modifier.size(32.dp)
        )
    }
}
