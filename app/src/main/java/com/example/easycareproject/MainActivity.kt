package com.example.easycareproject

import AppointmentViewModel
import MedicationReminderViewModel
import TimerScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.easycareproject.data.database.AppDatabase
import com.example.easycareproject.data.database.User
import com.example.easycareproject.data.repository.UserRepository
import com.example.easycareproject.ui.theme.screens.*
import com.example.easycareproject.ui.theme.viewmodel.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            // Database and repository initialization
            val userDao = AppDatabase.getDatabase(application).userDao()
            val userRepository = UserRepository(userDao)

            // Initialize ViewModels
            val userViewModel: UserViewModel = ViewModelProvider(
                this,
                UserViewModelFactory(userRepository)
            )[UserViewModel::class.java]

            val signupViewModel: SignupViewModel = ViewModelProvider(
                this,
                SignupViewModelFactory(userRepository)
            )[SignupViewModel::class.java]

            val healthInsightsViewModel: HealthInsightsViewModel = viewModel()

            val dailyTipsViewModel: DailyTipsViewModel = viewModel()

            val appointmentViewModel : AppointmentViewModel = viewModel()

            val medicationReminderViewModel: MedicationReminderViewModel = viewModel()

            AppNavigation(
                navController = navController,
                userViewModel = userViewModel,
                signupViewModel = signupViewModel,
                healthInsightsViewModel = healthInsightsViewModel,
                dailyTipsViewModel = dailyTipsViewModel,
                appointmentViewModel = appointmentViewModel,
                medicationReminderViewModel = medicationReminderViewModel
            )
        }
    }
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    userViewModel: UserViewModel,
    signupViewModel: SignupViewModel,
    healthInsightsViewModel: HealthInsightsViewModel,
    dailyTipsViewModel: DailyTipsViewModel,
    appointmentViewModel: AppointmentViewModel,
    medicationReminderViewModel: MedicationReminderViewModel
) {
    Box(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = "login_screen"
        ) {
            composable("login_screen") {
                LoginScreen(navController, userViewModel)
            }
            composable("signup_screen") {
                SignUpScreen(navController = navController, signupViewModel = signupViewModel)
            }
            composable("home_screen") {
                HomeScreen(navController = navController)
            }
            composable("dashboard_screen") {
                DashboardScreen(
                    navController = navController,
                    dailyTipsViewModel = dailyTipsViewModel,
                    healthInsightsViewModel = healthInsightsViewModel,
                    appointmentViewModel = appointmentViewModel,
                    user = User(id = 1, username = "Demo1", password = "Demo1")
                )
            }
            composable("health_insight_screen") {
                HealthInsightsScreen(navController = navController, healthInsightsViewModel = healthInsightsViewModel)
            }
            composable("pharmacy_contact") {
                PharmacyContactScreen(navController = navController)
            }
            composable("daily_tips") {
                DailyTipsScreen(navController = navController, dailyTipsViewModel = dailyTipsViewModel)
            }
            composable("appointment_scheduler_screen") {
                AppointmentSchedulerScreen(navController = navController, appointmentViewModel = appointmentViewModel)
            }
            composable("medication_reminder_screen") {
                MedicationReminderScreen(navController = navController, medicationReminderViewModel = medicationReminderViewModel)
            }
            composable("user_info_screen") {
                MyInfoScreen(
                    navController = navController,
                    user = User(id = 1, username = "Demo1", password = "Demo1"),
                    onSave = { updatedUser ->
                        println("Updated user: $updatedUser")
                    }
                )
            }
            composable("timer_screen") {
                TimerScreen(navController = navController)
            }
        }
    }
}
