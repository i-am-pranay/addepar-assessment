package com.example.addepar.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.addepar.ui.components.InvestmentItem
import com.example.addepar.ui.viewModel.InvestmentViewModel


@Composable
fun InvestmentScreen(
    investmentViewModel: InvestmentViewModel = hiltViewModel()
) {
        val isLoading by investmentViewModel.isLoading.collectAsState()
        val investmentList by investmentViewModel.investmentList.collectAsState()

        LaunchedEffect(key1 = Unit) {
            if (investmentList.isEmpty()) {
                investmentViewModel.getInvestment()
            }
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            if (isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.then(Modifier.size(32.dp))
                )
            } else {
               LazyColumn {
                   items(investmentList) {
                       InvestmentItem(investment = it)
                   }
               }
            }
        }
}
