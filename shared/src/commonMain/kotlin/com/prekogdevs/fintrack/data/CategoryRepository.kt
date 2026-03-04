package com.prekogdevs.fintrack.data

import com.prekogdevs.fintrack.domain.Category

interface CategoryRepository {
    suspend fun getCategories(): List<Category>
}
