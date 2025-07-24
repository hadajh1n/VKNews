package com.example.vk

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vk.databinding.ActivityMainBinding
import com.vk.id.AccessToken
import com.vk.id.VKID
import com.vk.id.auth.VKIDAuthCallback
import com.vk.id.VKIDAuthFail

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val authCallback = object : VKIDAuthCallback {
        override fun onAuth(accessToken: AccessToken) {
            Log.d("VKID", "Токен: ${accessToken.token}")
            Toast.makeText(this@MainActivity, "Вошли успешно!", Toast.LENGTH_SHORT).show()
        }

        override fun onFail(fail: VKIDAuthFail) {
            Toast.makeText(this@MainActivity, "Ошибка: ${fail::class.simpleName}", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val vkid = VKID.instance

        binding.btnAuthVk.setOnClickListener {
            vkid.authorize(this, authCallback)
        }
    }

}
