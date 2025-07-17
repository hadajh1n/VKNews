package com.example.vknews

import org.json.JSONObject

data class VKUser(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val photo200: String
) {
    companion object {
        fun parse(json: JSONObject): VKUser {
            return VKUser(
                id = json.getInt("id"),
                firstName = json.getString("first_name"),
                lastName = json.getString("last_name"),
                photo200 = json.getString("photo_200")
            )
        }
    }
}