package com.example.easycareproject.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.easycareproject.ui.theme.viewmodel.DailyTipsViewModel

@Composable
fun DailyTipsScreen(
    navController: NavController,
    dailyTipsViewModel: DailyTipsViewModel
) {
    val progress by dailyTipsViewModel.progress.observeAsState(initial = 0)
    val goals by dailyTipsViewModel.goals.observeAsState(initial = listOf(false, false, false))
    val descriptions by dailyTipsViewModel.goalDescriptions.observeAsState(initial = listOf("", "", ""))

    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Daily Tips Progress",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary
        )

        goals.forEachIndexed { index, isChecked ->
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Goal ${index + 1}", modifier = Modifier.weight(1f))
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { dailyTipsViewModel.updateGoal(index, it) }
                    )
                }

                // TextField for goal description
                OutlinedTextField(
                    value = descriptions[index],
                    onValueChange = { dailyTipsViewModel.updateGoalDescription(index, it) },
                    label = { Text("Enter your own goal description") },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        Text(
            text = "$progress% completed",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
                .background(MaterialTheme.colorScheme.surfaceVariant)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width((progress * 100).dp)
                    .background(MaterialTheme.colorScheme.primary)
            )
        }

        Button(
            onClick = {
                dailyTipsViewModel.updateProgress()
                navController.popBackStack()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Text("Update Progress and Return")
        }

        if (errorMessage.isNotEmpty()) {
            Text(
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.align(Alignment.Start)
            )
        }

        Text(
            text = "Press the button to save and go back",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
        )
    }
}
