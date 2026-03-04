package com.prekogdevs.fintrack.data.remote.dto

import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    val id: String,
    val name: String,
    val icon: String,
    val color: String
)
