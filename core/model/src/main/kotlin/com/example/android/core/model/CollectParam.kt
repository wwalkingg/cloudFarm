package com.example.android.core.model

import kotlinx.serialization.Serializable

@Serializable
data class CollectParam(
    val id:Int?,
    val productId:Int,
    val storeId:Int?
)
