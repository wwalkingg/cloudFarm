package com.example.feature.home

import feature.succecsscases.SuccessCaseScreen
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.feature.home.category.CategoryScreen
import com.example.feature.home.me.MeScreen
import com.example.feature.home.recommends.RecommendsScreen

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(component: HomeComponent) {
    LaunchedEffect(component.modelState.selected) {
        component.modelState.pagerState.scrollToPage(
            BottomMenus.values().indexOf(component.modelState.selected)
        )
    }
    Scaffold(
        contentWindowInsets = WindowInsets.systemBars,
        bottomBar = {
            HomeBottomBar(
                Modifier.fillMaxWidth(),
                component.modelState.selected,
                onSelected = { component.modelState.selected = it })
        }
    ) { padding ->
        Column(Modifier.padding(padding)) {
            HorizontalPager(
                pageCount = BottomMenus.values().size,
                state = component.modelState.pagerState,
                userScrollEnabled = false
            ) { page ->
                when (page) {
                    0 -> RecommendsScreen(component = component.recommendsComponent, onCategoryClick = {
                        component.jumpToCategory(it)
                    })

                    1 -> CategoryScreen(component = component.categoryComponent)
                    2 -> SuccessCaseScreen(component = component.successCasesComponent)
                    3 -> MeScreen(component = component.meComponent)
                }
            }
        }
    }
}