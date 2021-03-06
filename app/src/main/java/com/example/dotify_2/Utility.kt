package com.example.dotify_2

import android.hardware.lights.Light
import android.view.Window
import android.view.WindowInsetsController
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat

fun setFullScreen(window: Window){
    WindowCompat.setDecorFitsSystemWindows(window, false)
}

fun lightStatusBar(window: Window, isLight: Boolean = true){
    val wic = WindowInsetsControllerCompat(window,window.decorView)
    wic.isAppearanceLightStatusBars = isLight
}
