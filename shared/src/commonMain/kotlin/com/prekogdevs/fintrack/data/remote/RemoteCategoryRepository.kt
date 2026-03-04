package com.prekogdevs.fintrack.data.remote

import com.prekogdevs.fintrack.data.CategoryRepository
import com.prekogdevs.fintrack.domain.Category

class RemoteCategoryRepository(
    private val api: FintrackApi = FintrackApi()
) : CategoryRepository {
    override suspend fun getCategories(): List<Category> =
        api.getCategories().map { it.toDomain() }
}
