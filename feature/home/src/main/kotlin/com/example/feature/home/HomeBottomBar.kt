package com.example.feature.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.Article
import androidx.compose.material.icons.outlined.Category
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
internal fun HomeBottomBar(modifier: Modifier = Modifier, selected: BottomMenus, onSelected: (BottomMenus) -> Unit) {
    NavigationBar(modifier) {
        BottomMenus.values().forEach {
            NavigationBarItem(
                selected = selected == it,
                onClick = { onSelected(it) },
                icon = { Icon(if (selected == it) it.selectedIcon else it.icon, contentDescription = null) },
            )
        }
    }
}

internal enum class BottomMenus(
    val chineseName: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector
) {
    HOME("首页", Icons.Outlined.Home, Icons.Filled.Home),
    CATEGORY("分类", Icons.Outlined.Category, Icons.Filled.Category),
    SUCCESS_CASES("成功案例",Icons.Outlined.Article, Icons.Filled.Article ),
    ME("我的", Icons.Outlined.AccountBox, Icons.Filled.AccountBox)
}