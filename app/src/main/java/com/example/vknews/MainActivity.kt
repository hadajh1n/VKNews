package com.example.vknews

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.vknews.databinding.ActivityMainBinding
import com.vk.api.sdk.VK
import com.vk.api.sdk.VKApiCallback
import com.vk.api.sdk.auth.VKAuthenticationResult
import com.vk.api.sdk.auth.VKScope

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Привязываем авторизацию к нажатию кнопки
        binding.loginButton.setOnClickListener {
            val authLauncher = VK.login(this) { result: VKAuthenticationResult ->
                when (result) {
                    is VKAuthenticationResult.Success -> {
                        // Успешная авторизация, получаем данные пользователя
                        fetchCurrentUser()
                    }
                    is VKAuthenticationResult.Failed -> {
                        // Ошибка авторизации
                        Log.e(TAG, "Ошибка авторизации: ${result.exception}")
                    }
                }
            }
            authLauncher.launch(arrayListOf(VKScope.WALL, VKScope.PHOTOS))
        }
    }

    // Функция для получения данных текущего пользователя
    private fun fetchCurrentUser() {
        VK.execute(VKUsersCommand(), object : VKApiCallback<List<VKUser>> {
            override fun success(result: List<VKUser>) {
                if (result.isNotEmpty()) {
                    val currentUser = result[0]
                    Log.d(TAG, "Текущий пользователь: ${currentUser.firstName} ${currentUser.lastName}")
                    // Здесь можно обновить UI, например, вывести имя в TextView
                }
            }

            override fun fail(error: Exception) {
                Log.e(TAG, "Ошибка получения данных: $error")
            }
        })
    }
}