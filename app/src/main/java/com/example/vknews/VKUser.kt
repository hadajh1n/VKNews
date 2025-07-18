package com.example.vknews

import org.json.JSONObject

data class VKUser(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val photo200: String?
) {
    companion object {
        fun parse(json: JSONObject): VKUser {
            return VKUser(
                id = json.getInt("id"),
                firstName = json.optString("first_name", ""),
                lastName = json.optString("last_name", ""),
                photo200 = json.optString("photo_200", null)
            )
        }
    }
}