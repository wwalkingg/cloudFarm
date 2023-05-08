package com.example.core.design_system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun SystemBar(statusBar: Color, navigationBar: Color = Color.Transparent) {
    val systemUiController = rememberSystemUiController()
    SideEffect {
        systemUiController.setStatusBarColor(statusBar)
        systemUiController.setNavigationBarColor(Color.Transparent)
    }
}