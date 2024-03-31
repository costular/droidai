package com.costular.droidai.features.settings.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.costular.droidai.ui.theme.DroidAITheme

@Composable
fun SettingItem(
    title: @Composable () -> Unit,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    start: @Composable (RowScope.() -> Unit)? = null,
    end: @Composable (RowScope.() -> Unit)? = null,
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .clickable { onClick() }
            .padding(16.dp)
            .semantics(mergeDescendants = true) {},
    ) {
        val onSurfaceVariant = MaterialTheme.colorScheme.onSurfaceVariant
        CompositionLocalProvider(LocalContentColor provides onSurfaceVariant) {
            if (start != null) {
                start()
                Spacer(Modifier.width(24.dp))
            }

            Box(Modifier.weight(1f)) {
                title()
            }

            if (end != null) {
                Spacer(Modifier.width(16.dp))
                end()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun JustTitlePreview() {
    DroidAITheme {
        SettingItem(
            title = {
                Text("This is a test with only a title, nothing else")
            },
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TitleAndStartPreview() {
    DroidAITheme {
        SettingItem(
            title = {
                Text("This is a test with start also")
            },
            start = {
                Icon(Icons.Default.Search, null)
            },
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TitleAndStartAndEndPreview() {
    DroidAITheme {
        SettingItem(
            title = {
                Text("This is a test with start and end wadwadawdaw daw daw daw awdaw dawd sdadad")
            },
            start = {
                Icon(Icons.Default.Search, null)
            },
            end = {
                Switch(
                    checked = true,
                    onCheckedChange = { },
                )
            },
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TitleWithDescriptionAndEndPreview() {
    DroidAITheme {
        SettingItem(
            title = {
                Column {
                    Text(
                        "This is a title",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Spacer(Modifier.height(8.dp))
                    Text(
                        "This is description wadwadawdaw daw daw daw awdaw dawd sdadad",
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            },
            end = {
                Switch(
                    checked = true,
                    onCheckedChange = { },
                )
            },
            onClick = {},
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
