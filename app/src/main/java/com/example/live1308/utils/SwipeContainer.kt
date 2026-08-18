package com.example.live1308.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.live1308.R


@Composable
fun SwipeContainer() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
            .padding(end = 20.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_delete),
            contentDescription = null,
            modifier = Modifier
                .background(Color.White)
        )
    }
}