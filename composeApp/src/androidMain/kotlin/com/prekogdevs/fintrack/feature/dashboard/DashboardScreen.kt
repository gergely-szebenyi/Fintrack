package com.prekogdevs.fintrack.feature.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.prekogdevs.fintrack.core.theme.AppColors
import com.prekogdevs.fintrack.feature.dashboard.components.AddTransactionDialog
import com.prekogdevs.fintrack.feature.dashboard.components.BalanceCard
import com.prekogdevs.fintrack.feature.dashboard.components.RecentTransactionsHeader
import com.prekogdevs.fintrack.feature.dashboard.components.SpendingByCategoryCard
import com.prekogdevs.fintrack.feature.dashboard.components.TopBar
import com.prekogdevs.fintrack.feature.dashboard.components.TransactionItem

@Composable
fun DashboardScreen(
    viewModel: AndroidDashboardViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val categoryIconMap = state.availableCategories.associateBy({ it.name }, { it.icon })

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.BgGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp)
        ) {
            TopBar()
            BalanceCard(state.totalBalance, state.income, state.expenses)
            SpendingByCategoryCard(state.categories)
            RecentTransactionsHeader()

            state.transactions.forEach { transaction ->
                TransactionItem(
                    transaction = transaction,
                    icon = categoryIconMap[transaction.category] ?: ""
                )
            }
        }

        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalAlignment = Alignment.End
        ) {
            FloatingActionButton(
                onClick = { viewModel.onAddClicked() },
                containerColor = AppColors.BluePrimary,
                contentColor = AppColors.BgGray
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Transaction")
            }
        }

        if (state.showAddDialog) {
            AddTransactionDialog(
                categories = state.availableCategories,
                onDismiss = { viewModel.onDialogDismissed() },
                onAddTransaction = viewModel::onAddTransaction
            )
        }
    }
}
