package com.costular.droidai.features.settings.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FlashOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.costular.droidai.ui.theme.DroidAITheme

@Composable
fun SettingSwitch(
    title: @Composable () -> Unit,
    isSelected: Boolean,
    onSelect: (isSelected: Boolean) -> Unit,
    modifier: Modifier = Modifier,
    start: @Composable (RowScope.() -> Unit)? = null,
) {
    SettingItem(
        title = title,
        start = start,
        onClick = { onSelect(!isSelected) },
        modifier = modifier,
        end = {
            Switch(checked = isSelected, onCheckedChange = { onSelect(it) })
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun SettingSwitchPreview() {
    var isSelected by remember {
        mutableStateOf(true)
    }

    DroidAITheme {
        SettingSwitch(
            title = {
                Text("This is a checkbox sample")
            },
            isSelected = isSelected,
            onSelect = { isSelected = it },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingSwitchWithIconPreview() {
    var isSelected by remember {
        mutableStateOf(true)
    }

    DroidAITheme {
        SettingSwitch(
            title = {
                Text("This is a checkbox sample")
            },
            isSelected = isSelected,
            onSelect = { isSelected = it },
            start = {
                Icon(imageVector = Icons.Outlined.FlashOn, contentDescription = null)
            },
        )
    }
}
