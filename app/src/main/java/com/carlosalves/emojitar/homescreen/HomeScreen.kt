package com.carlosalves.emojitar.homescreen

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.Android
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosalves.emojitar.R
import coil3.compose.AsyncImage
import com.carlosalves.emojitar.ui.theme.EmojitarTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    uiState: HomeUiState,
    onGetEmojiClick: () -> Unit,
    onRandomEmojiClick: () -> Unit,
    onEmojiListClick: () -> Unit,
    onAvatarSearch: (String) -> Unit,
    onAvatarListClick: () -> Unit,
    onGoogleReposClick: () -> Unit,
) {

    var userName by rememberSaveable { mutableStateOf("") }
    val emojiListLoaded = uiState.emojiListPopulated

    val noEmojiToast = Toast.makeText(LocalContext.current, stringResource(R.string.list_not_loaded), Toast.LENGTH_SHORT)
    val noAvatarToast = Toast.makeText(LocalContext.current, stringResource(R.string.no_avatar_available), Toast.LENGTH_SHORT)

    if (uiState.isLoading) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.3f)),
            contentAlignment = Alignment.Center
        ) {

            CircularProgressIndicator()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4A90E2))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        if (emojiListLoaded) {
            AsyncImage(
                model = uiState.currentEmoji?.imageUrl,
                contentDescription = uiState.currentEmoji?.name,
                modifier = Modifier.size(200.dp)
            )
        } else {
            Icon(
                imageVector = Icons.Default.Android,
                contentDescription = null,
                modifier = Modifier.size(200.dp)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {if (emojiListLoaded) onRandomEmojiClick() else onGetEmojiClick()},
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(if (emojiListLoaded) stringResource(R.string.random_emoji) else stringResource(R.string.get_emojis))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (emojiListLoaded) {
                    onEmojiListClick()
                } else {
                    noEmojiToast.show()
                }
            },
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(stringResource(R.string.emoji_list))
        }

        Spacer(modifier = Modifier.height(64.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {

            OutlinedTextField(
                value = userName,
                onValueChange = {newUsername -> userName = newUsername},
                modifier = Modifier
                    .fillMaxWidth(0.5f),
                singleLine = true,
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White
                )
            )

            Spacer(modifier = Modifier.width(12.dp))

            IconButton(
                onClick = { onAvatarSearch(userName) },
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.primary,
                        shape = RectangleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_avatar),
                    tint = Color.White
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if (uiState.avatarsLoaded) {
                    onAvatarListClick()
                } else {
                     noAvatarToast.show()
                }
            },
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(stringResource(R.string.avatar_list))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onGoogleReposClick,
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(stringResource(R.string.google_repos))
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
private fun HomeScreenPreview() {

    EmojitarTheme {
        HomeScreen(
            uiState = HomeUiState(),
            onGetEmojiClick = {},
            onRandomEmojiClick = {},
            onEmojiListClick = {},
            onAvatarSearch = {},
            onAvatarListClick = {},
            onGoogleReposClick = {}
        )
    }
}
