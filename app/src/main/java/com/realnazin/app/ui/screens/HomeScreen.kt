package com.realnazin.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.realnazin.app.ui.components.FloatingCounter

@Composable
fun HomeScreen() {
    // محاكاة لعدد الفيديوهات المشاهدة (سيتم ربطها بقاعدة بيانات Firestore مباشرة لاحقاً)
    var totalVideos by remember { mutableStateOf(42) }
    val currentUserEmail = FirebaseAuth.getInstance().currentUser?.email ?: "User"

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // محتوى الشاشة الأساسي في المنتصف
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "REAL NAZIN DASHBOARD",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 22.sp,
                fontWeight = FontWeight.Black
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Logged in as: $currentUserEmail",
                color = MaterialTheme.colorScheme.secondary,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "Drag the glowing circle anywhere on the screen!",
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f),
                fontSize = 12.sp
            )
        }

        // الدائرة العائمة المتحركة التي تحتوي على إجمالي الفيديوهات وتتحرك في أي مكان
        FloatingCounter(videoCount = totalVideos)
    }
}