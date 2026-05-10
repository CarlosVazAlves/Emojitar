package com.carlosalves.emojitar.reposcreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.carlosalves.emojitar.model.Repo

@Composable
fun RepoScreen(
    repos: LazyPagingItems<Repo>
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().background(Color(0xFF4A90E2)),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = repos.itemCount
        ) { index ->
            val repo = repos[index]
            if (repo != null) {
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFF5F9FF)
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(
                            text = repo.fullName,
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                }
            }
        }
        repos.apply {
            when(loadState.append) {
                is LoadState.Loading -> {
                    item {
                        Box(
                            modifier = Modifier.fillMaxWidth(),
                            contentAlignment = Alignment.Center,
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
                else -> Unit
            }
        }
    }
}