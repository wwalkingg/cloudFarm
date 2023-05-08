package com.example.android.core.model

import kotlinx.serialization.Serializable

@Serializable
data class UploadResponse(
    val url: String? = null,
    val error: String? = null
)
