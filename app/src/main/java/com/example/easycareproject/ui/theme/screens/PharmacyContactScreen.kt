package com.example.easycareproject.ui.theme.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PharmacyContactScreen(navController: NavController) {
    val isOrderConfirmed = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF74B9FF),
                        Color(0xFF0984E3)
                    )
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.padding(24.dp)
        ) {
            // Title
            Text(
                text = "Pharmacy Contact",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            // Subtitle
            Text(
                text = "Contact the nearest pharmacy for your medication needs",
                fontSize = 18.sp,
                color = Color.White.copy(alpha = 0.8f),
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Call Button
            Button(
                onClick = { /* Add phone call logic here */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp),
                //elevation = ButtonDefaults.elevation(8.dp)
            ) {
                Text(text = "Call Pharmacy", fontSize = 16.sp)
            }
            Spacer(modifier = Modifier.height(20.dp))

            // Checkbox and Confirmation
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
            ) {
                Checkbox(
                    checked = isOrderConfirmed.value,
                    onCheckedChange = { isChecked -> isOrderConfirmed.value = isChecked },
                    colors = CheckboxDefaults.colors(checkedColor = Color(0xFF0984E3))
                )
                Text(
                    text = if (isOrderConfirmed.value) "Order Confirmed" else "Confirm Order",
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = { navController.navigateUp() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp),
                shape = RoundedCornerShape(16.dp),
                //elevation = ButtonDefaults.elevation(8.dp)
            ) {
                Text(text = "Back to Dashboard", fontSize = 16.sp)
            }
        }
    }
}
