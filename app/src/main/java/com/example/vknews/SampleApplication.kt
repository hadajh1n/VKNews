package com.example.vknews

import android.app.Application
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKTokenExpiredHandler

class SampleApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        VK.initialize(this) // Для 4.1.0 достаточно контекста
        VK.addTokenExpiredHandler(tokenTracker)
    }

    private val tokenTracker = object : VKTokenExpiredHandler {
        override fun onTokenExpired() {
            android.util.Log.d("VKToken", "Токен истек, требуется повторная авторизация")
        }
    }
}