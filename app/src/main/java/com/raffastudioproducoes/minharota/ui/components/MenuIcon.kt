package com.raffastudioproducoes.minharota.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MenuIconTwoLines() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Divider(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(2.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier = Modifier.height(4.dp))
        Divider(
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .height(2.dp),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}
