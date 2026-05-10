package com.carlosalves.emojitar.avatarlistscreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.carlosalves.emojitar.model.Avatar

@Composable
fun AvatarListScreen(
    uiState: AvatarListUiState,
    onAvatarClick: (Avatar) -> Unit
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        items(
            items = uiState.avatars,
            key = { avatar -> avatar.username }
        ) { avatar ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onAvatarClick(avatar)
                    }
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    AsyncImage(
                        model = avatar.avatarUrl,
                        contentDescription = avatar.username,
                        modifier = Modifier.size(96.dp)
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )

                    Text(
                        text = avatar.username,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }
        }
    }
}