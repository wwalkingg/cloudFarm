package com.example.android.navigation

import AddressManagementComponent
import CollectionProductComponent
import LoginComponent
import OrderManagementComponent
import RewardPointsComponent
import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.value.Value
import com.example.feature.home.HomeComponent
import core.common.NavConfig
import core.common.navigation
import feature.all_articles.AllArticlesComponent
import feature.article_detail.ArticleDetailComponent
import feature.modifier_userinfo.ModifierUserinfoComponent
import feature.product_detail.ProductDetailComponent
import feature.store_detail.StoreDetailComponent
import feature.succecsscases.detail.SuccessCaseDetailComponent
import feature.succecsscases.image_creator.SuccessCaseImageCreatorComponent
import feature.succecsscases.my.MyCaseComponent
import feature.succecsscases.video_creator.SuccessCaseVideoCreatorComponent

class RootComponent(componentContext: ComponentContext) : ComponentContext by componentContext {

    private val _childStack =
        childStack(
            source = navigation,
            initialConfiguration = NavConfig.Splash,
            handleBackButton = true,
            childFactory = ::createChild,
        )

    internal val stack: Value<ChildStack<NavConfig, Child>> = _childStack

    private fun createChild(config: NavConfig, componentContext: ComponentContext): Child =
        when (config) {
            is NavConfig.Home -> Child.Home(HomeComponent(componentContext))
            NavConfig.AllArticles -> Child.AllArticles(AllArticlesComponent(componentContext))
            is NavConfig.ArticleDetail -> Child.ArticleDetail(
                ArticleDetailComponent(
                    componentContext,
                    config.id,
                    config.title
                )
            )

            is NavConfig.ProductDetail -> Child.ProductDetail(
                ProductDetailComponent(
                    componentContext,
                    config.id
                )
            )

            NavConfig.AddressManagement -> Child.AddressManagement(
                AddressManagementComponent(
                    componentContext
                )
            )

            NavConfig.CollectionProduct -> Child.CollectionProduct(
                CollectionProductComponent(
                    componentContext
                )
            )

            NavConfig.ModifierUserinfo -> Child.ModifierUserinfo(
                ModifierUserinfoComponent(
                    componentContext
                )
            )

            NavConfig.Login -> Child.Login(LoginComponent(componentContext))
            NavConfig.OrderManagement -> Child.OrderManagement(
                OrderManagementComponent(
                    componentContext
                )
            )

            NavConfig.RewardPoints -> Child.RewardPoints(RewardPointsComponent(componentContext))
            is NavConfig.StoreDetail -> Child.StoreDetail(StoreDetailComponent(componentContext, config.id))
            NavConfig.SuccessVideoCaseCreator -> Child.SuccessVideoCaseCreator(
                SuccessCaseVideoCreatorComponent(
                    componentContext
                )
            )

            is NavConfig.SuccessCaseDetail -> Child.SuccessCaseDetail(
                SuccessCaseDetailComponent(
                    componentContext,
                    config.id
                )
            )

            NavConfig.SuccessImageCaseCreator -> Child.SuccessImageCaseCreator(
                SuccessCaseImageCreatorComponent(
                    componentContext
                )
            )

            NavConfig.MyCase -> Child.MyCase(MyCaseComponent(componentContext))
            NavConfig.Splash -> Child.Splash
        }


    internal sealed interface Child {
        data class Home(val component: HomeComponent) : Child
        data class AllArticles(val component: AllArticlesComponent) : Child
        data class ArticleDetail(val component: ArticleDetailComponent) : Child
        data class ProductDetail(val component: ProductDetailComponent) : Child

        data class AddressManagement(val component: AddressManagementComponent) : Child
        data class CollectionProduct(val component: CollectionProductComponent) : Child
        data class ModifierUserinfo(val component: ModifierUserinfoComponent) : Child
        data class Login(val component: LoginComponent) : Child
        data class OrderManagement(val component: OrderManagementComponent) : Child
        data class RewardPoints(val component: RewardPointsComponent) : Child
        data class StoreDetail(val component: StoreDetailComponent) : Child
        data class SuccessVideoCaseCreator(val component: SuccessCaseVideoCreatorComponent) : Child
        data class SuccessImageCaseCreator(val component: SuccessCaseImageCreatorComponent) : Child
        data class SuccessCaseDetail(val component: SuccessCaseDetailComponent) : Child
        data class MyCase(val component: MyCaseComponent) : Child
        object Splash:Child
    }
}
