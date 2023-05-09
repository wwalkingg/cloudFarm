package com.example.android.core.model

import kotlinx.serialization.Serializable

@Serializable
data class SuccessCase(
    val id: Int,
    val customerId: Int,
    val images: List<ArticleImage>,
    val video: String,
    val title: String,
    val content: String,
    val createTime: String,
    val topic: List<ArticleTopic>,
    val name: String,
    val avatar: String
)
@Serializable
data class ArticleImage(
    val id:Int,
    val articleId:Int,
    val image:String
)

@Serializable
data class ArticleTopic(
    val id:Int,
    val articleId: Int,
    val topic:String
)