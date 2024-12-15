package com.example.easycareproject.ui.theme.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DeliveryRequestCard(
    navController: NavController,
    onRequestUpdate: (Boolean) -> Unit
) {
    var isRequested by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                navController.navigate("pharmacy_contact")
            },
        shape = MaterialTheme.shapes.medium.copy(CornerSize(12.dp)),
        colors = CardDefaults.cardColors(
            containerColor = if (isRequested) Color(0xFFD4E157) else Color(0xFFFFFFFF)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = if (isRequested) "Medication Requested" else "Request Medication",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    text = if (isRequested)
                        "Your request has been sent."
                    else
                        "Tap to contact your pharmacy.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Spacer(modifier = Modifier.width(16.dp))
            Checkbox(
                checked = isRequested,
                onCheckedChange = { isChecked ->
                    isRequested = isChecked
                    onRequestUpdate(isChecked)
                }
            )
        }
    }
}
