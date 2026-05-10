package com.carlosalves.emojitar.emojilistscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.material3.pulltorefresh.rememberPullToRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.carlosalves.emojitar.model.Emoji
import com.carlosalves.emojitar.ui.theme.EmojitarTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EmojiListScreen(
    uiState: EmojiListUiState,
    onEmojiClick: (Emoji) -> Unit,
    onRefresh: () -> Unit
) {

    val pullToRefreshState = rememberPullToRefreshState()

    PullToRefreshBox(
        isRefreshing = uiState.isRefreshing,
        onRefresh = onRefresh,
        state = pullToRefreshState,
        modifier = Modifier.fillMaxSize()
    ) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            items(
                items = uiState.emojis,
                key = { emoji -> emoji.name }
            ) { emoji ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onEmojiClick(emoji)
                        }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(12.dp)
                            .fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        AsyncImage(
                            model = emoji.imageUrl,
                            contentDescription = emoji.name,
                            modifier = Modifier.size(64.dp)
                        )
                        Spacer(
                            modifier = Modifier.height(8.dp)
                        )
                        Text(
                            text = emoji.name,
                            maxLines = 1
                        )
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun EmojiListScreenPreview() {
    EmojitarTheme {
        EmojiListScreen(
            uiState = EmojiListUiState(
                emojis = listOf(
                    Emoji("smile", ""),
                    Emoji("rocket", ""),
                    Emoji("android", ""),
                    Emoji("fire", ""),
                    Emoji("star", ""),
                    Emoji("heart", "")
                )
            ),
            onEmojiClick = {},
            onRefresh = {}
        )
    }
}