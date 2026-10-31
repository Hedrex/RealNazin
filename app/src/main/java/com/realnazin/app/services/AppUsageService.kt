package com.realnazin.app.services

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class AppUsageService : AccessibilityService() {

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        if (event?.eventType == AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED) {
            val packageName = event.packageName?.toString() ?: ""

            // التحقق مما إذا كان المستخدم فتح تيك توك أو إنستجرام
            if (packageName.contains("instagram") || packageName.contains("tiktok") || packageName.contains("youtube")) {
                incrementReelCount()
            }
        }
    }

    private fun incrementReelCount() {
        val userId = FirebaseAuth.getInstance().currentUser?.uid ?: return
        val db = FirebaseFirestore.getInstance()

        // زيادة العداد في قاعدة البيانات للسحابية ليراه الأصدقاء فوراً
        val userRef = db.collection("users").document(userId)
        db.runTransaction { transaction ->
            val snapshot = transaction.get(userRef)
            val currentCount = snapshot.getLong("dailyReels") ?: 0L
            transaction.update(userRef, "dailyReels", currentCount + 1)
        }
    }

    override fun onInterrupt() {}
}