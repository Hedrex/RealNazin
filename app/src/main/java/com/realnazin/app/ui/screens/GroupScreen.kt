package com.realnazin.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import kotlin.random.Random

@Composable
fun GroupScreen(onGroupJoined: () -> Unit) {
    var username by remember { mutableStateOf("") }
    var inviteCodeInput by remember { mutableStateOf("") }
    var groupNameInput by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }

    val db = FirebaseFirestore.getInstance()
    val currentUser = FirebaseAuth.getInstance().currentUser

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "SETUP YOUR PROFILE",
                style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary)
            )
            Spacer(modifier = Modifier.height(24.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("Your Nickname (e.g. Nazin)") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            Divider(color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.3f))
            Spacer(modifier = Modifier.height(16.dp))

            // Join Group Section
            Text(text = "Join Friend's Group", color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = inviteCodeInput,
                onValueChange = { inviteCodeInput = it },
                label = { Text("Enter Invite Code") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (username.isBlank() || inviteCodeInput.isBlank()) {
                        errorMessage = "Please enter your name and invite code"
                        return@Button
                    }
                    isLoading = true
                    // يمكنك ربط عملية الانضمام بقاعدة البيانات هنا لاحقاً
                    isLoading = false
                    onGroupJoined()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Join Group")
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Create Group Section
            Text(text = "Or Create New Group", color = MaterialTheme.colorScheme.secondary, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedTextField(
                value = groupNameInput,
                onValueChange = { groupNameInput = it },
                label = { Text("Group Name") },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    if (username.isBlank() || groupNameInput.isBlank()) {
                        errorMessage = "Please enter your name and group name"
                        return@Button
                    }
                    isLoading = true
                    // إنشاء كود دعوة عشوائي للمجموعة
                    val randomCode = (1000..9999).random().toString()
                    isLoading = false
                    onGroupJoined()
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.secondary)
            ) {
                Text("Create Group", color = MaterialTheme.colorScheme.background)
            }

            if (errorMessage.isNotEmpty()) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
            }
        }
    }
}