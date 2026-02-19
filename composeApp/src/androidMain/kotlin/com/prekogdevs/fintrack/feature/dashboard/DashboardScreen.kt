package com.prekogdevs.fintrack.feature.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
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
    viewModel: DashboardViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.BgGray)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 100.dp) // extra padding so FAB doesn't cover content
        ) {
            TopBar()
            BalanceCard(state.totalBalance, state.income, state.expenses)
            SpendingByCategoryCard(state.categories)
            RecentTransactionsHeader()

            state.transactions.forEach {
                TransactionItem(it)
            }
        }

        FloatingActionButton(
            onClick = { viewModel.onEvent(Event.AddClicked) },
            containerColor = AppColors.BluePrimary,
            contentColor = AppColors.BgGray,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(24.dp)
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add Transaction")
        }

        // Dialog
        if (state.showAddDialog) {
            AddTransactionDialog {
                viewModel.onEvent(Event.DialogDismissed)
            }
        }
    }
}
