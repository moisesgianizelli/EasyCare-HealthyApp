import android.content.Context
import android.content.Intent
import android.hardware.camera2.CameraManager
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FlashlightOn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Phone

@Composable
fun EmergencyAndFlashlightButtons() {
    val context = LocalContext.current
    val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    val cameraId = cameraManager.cameraIdList.firstOrNull()
    val isFlashOn = remember { mutableStateOf(false) }

    Row(
        horizontalArrangement = Arrangement.SpaceEvenly,
        modifier = Modifier.padding(16.dp)
    ) {
        EmergencyButton()

        Button(
            onClick = {
                if (cameraId != null) {
                    isFlashOn.value = !isFlashOn.value
                    try {
                        cameraManager.setTorchMode(cameraId, isFlashOn.value)
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Yellow,
                contentColor = Color.Black
            ),
            shape = androidx.compose.foundation.shape.CircleShape,
            modifier = Modifier.size(64.dp)
        ) {
            Icon(
                imageVector = Icons.Default.FlashlightOn,
                contentDescription = "Flashlight",
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@Composable
fun EmergencyButton() {
    val context = LocalContext.current

    Button(
        onClick = {
            // Open phone dialer with a preset emergency number
            val intent = Intent(Intent.ACTION_DIAL).apply {
                data = Uri.parse("tel:999")
            }
            context.startActivity(intent)
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red,
            contentColor = Color.White
        ),
        shape = androidx.compose.foundation.shape.CircleShape,
        modifier = Modifier.size(64.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Phone,
            contentDescription = "Emergency Phone",
            modifier = Modifier.size(32.dp)
        )
    }
}
