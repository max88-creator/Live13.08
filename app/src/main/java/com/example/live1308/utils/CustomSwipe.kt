package com.example.live1308.utils

import androidx.compose.material3.ListItem
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import com.example.live1308.data.User

@Composable
fun CustomSwipe(
    onDelete: () -> Unit,
    item: User
) {
    val swipeToDeleteState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart) {
                onDelete()
                true
            } else {
                false
            }
        }
    )
    SwipeToDismissBox(
        state = swipeToDeleteState,
        backgroundContent = {
            SwipeContainer()
        },
        enableDismissFromStartToEnd = false
    ) {
        ListItem(
            headlineContent = {

            }
        )
    }
}