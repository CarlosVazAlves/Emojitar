package com.carlosalves.emojitar.homescreen

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosalves.emojitar.ui.theme.EmojitarTheme
import com.carlosalves.emojitar.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    var userName by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF4A90E2))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = Icons.Default.Android,
            contentDescription = null,
            modifier = Modifier.size(200.dp)
        )

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(stringResource(R.string.random_emoji))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {},
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
                onClick = {},
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
            onClick = {},
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(stringResource(R.string.avatar_list))
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {},
            modifier = Modifier.fillMaxWidth(0.7f)
        ) {
            Text(stringResource(R.string.google_repos))
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview() {
    EmojitarTheme {
        HomeScreen()
    }
}