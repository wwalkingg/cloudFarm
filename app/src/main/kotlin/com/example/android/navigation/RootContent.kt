package com.example.android.navigation

import AddressManagementScreen
import CollectionProductScreen
import LoginScreen
import OrderManagementScreen
import RewardPointsScreen
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.jetpack.stack.Children
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetpack.stack.animation.stackAnimation
import com.example.feature.home.HomeScreen
import feature.succecsscases.video_creator.SuccessCaseVideoCreatorScreen
import feature.succecsscases.detail.SuccessCaseDetailScreen
import feature.all_articles.AllArticlesScreen
import feature.article_detail.ArticleDetailScreen
import feature.modifier_userinfo.ModifierUserinfoScreen
import feature.product_detail.ProductDetailScreen
import feature.store_detail.StoreDetailScreen
import feature.succecsscases.image_creator.SuccessCaseImageCreatorScreen

@Composable
fun RootContent(component: RootComponent, modifier: Modifier = Modifier) {
    Children(
        stack = component.stack,
        modifier = modifier,
        animation = stackAnimation(slide()),
    ) {
        when (val child = it.instance) {
            is RootComponent.Child.Home -> HomeScreen( child.component)
            is RootComponent.Child.AddressManagement -> AddressManagementScreen(component = child.component)
            is RootComponent.Child.AllArticles -> AllArticlesScreen(component = child.component)
            is RootComponent.Child.ArticleDetail -> ArticleDetailScreen(component = child.component)
            is RootComponent.Child.CollectionProduct -> CollectionProductScreen(component = child.component)
            is RootComponent.Child.ModifierUserinfo -> ModifierUserinfoScreen(component = child.component)
            is RootComponent.Child.Login -> LoginScreen(component = child.component)
            is RootComponent.Child.OrderManagement -> OrderManagementScreen(component = child.component)
            is RootComponent.Child.ProductDetail -> ProductDetailScreen(component = child.component)
            is RootComponent.Child.RewardPoints -> RewardPointsScreen(component = child.component)
            is RootComponent.Child.StoreDetail -> StoreDetailScreen(component = child.component)
            is RootComponent.Child.SuccessVideoCaseCreator -> SuccessCaseVideoCreatorScreen(component = child.component)
            is RootComponent.Child.SuccessCaseDetail -> SuccessCaseDetailScreen(component = child.component)
            is RootComponent.Child.SuccessImageCaseCreator -> SuccessCaseImageCreatorScreen(component = child.component)
        }
    }
}