package com.example.android.core.model

import kotlinx.serialization.Serializable

@Serializable
data class CaseParam(
    val title: String,
    val content: String,
    val images: List<String>,
    val video: String,
    val topic: List<String>
)