package com.realnazin.app.data

data class UserProfile(
    val userId: String = "",
    val username: String = "",
    val groupId: String = ""
)

data class Group(
    val groupId: String = "",
    val groupName: String = "",
    val inviteCode: String = "",
    val members: List<String> = emptyList()
)