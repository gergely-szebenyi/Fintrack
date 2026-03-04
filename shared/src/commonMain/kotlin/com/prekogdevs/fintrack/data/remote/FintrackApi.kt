package com.prekogdevs.fintrack.data.remote

import com.prekogdevs.fintrack.data.remote.dto.BudgetDto
import com.prekogdevs.fintrack.data.remote.dto.CategoryDto
import com.prekogdevs.fintrack.data.remote.dto.TransactionDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class FintrackApi(
    private val client: HttpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(Logging) {
            level = LogLevel.INFO
        }
    }
) {
    companion object {
        const val BASE_URL = "https://699711e77d17864365763bab.mockapi.io"
    }

    suspend fun getTransactions(): List<TransactionDto> =
        client.get("$BASE_URL/transactions").body()

    suspend fun getCategories(): List<CategoryDto> =
        client.get("$BASE_URL/categories").body()

    suspend fun getBudgets(): List<BudgetDto> =
        client.get("$BASE_URL/budgets").body()
}
