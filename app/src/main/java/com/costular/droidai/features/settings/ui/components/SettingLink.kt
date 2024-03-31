package com.costular.droidai.features.settings.ui.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.OpenInNew
import androidx.compose.material.icons.outlined.Code
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.costular.droidai.ui.theme.DroidAITheme

@Composable
fun SettingLink(
    title: @Composable () -> Unit,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    SettingItem(
        start = {
            Icon(
                painter = rememberVectorPainter(icon),
                contentDescription = null,
                modifier = Modifier
                    .size(24.dp)
                    .align(Alignment.Top),
            )
        },
        title = title,
        end = {
            Icon(
                Icons.AutoMirrored.Filled.OpenInNew,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
            )
        },
        onClick = onClick,
    )
}

@Composable
private fun SettingOptionPrev() {
    DroidAITheme {
        SettingLink(
            title = {
                Text(
                    text = "GitHub repository",
                    style = MaterialTheme.typography.titleSmall,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                )
            },
            icon = Icons.Outlined.Code,
            onClick = {},
        )
    }
}
