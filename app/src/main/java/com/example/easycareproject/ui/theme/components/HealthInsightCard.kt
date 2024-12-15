package com.example.easycareproject.ui.theme.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HealthAndSafety
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.easycareproject.ui.theme.viewmodel.HealthInsightsViewModel

@Composable
fun HealthInsightsCard(
    navController: NavController,
    viewModel: HealthInsightsViewModel
) {
    val insightValue by viewModel.insightValue.observeAsState(initial = 72)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("health_insight_screen")
            },
        shape = MaterialTheme.shapes.medium
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.HealthAndSafety,
                contentDescription = "Health Insights",
                modifier = Modifier.size(40.dp),
                tint = Color.Red
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = "Health Insights",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = "Latest metrics: $insightValue BPM.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}


