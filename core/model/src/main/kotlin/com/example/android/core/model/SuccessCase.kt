package com.example.android.core.model

import kotlinx.serialization.Serializable

@Serializable
data class SuccessCase(
    val id: Int,
    val customerId: Int,
    val images: List<String>,
    val video: String,
    val title: String,
    val content: String,
    val createTime: String,
    val topic: List<String>
)
