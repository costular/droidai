package com.costular.droidai.core.ui.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ProvideTextStyle
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.costular.droidai.ui.theme.DroidAITheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputDialog(
    title: @Composable () -> Unit,
    description: @Composable (() -> Unit)? = null,
    value: String,
    onDismiss: () -> Unit,

    onSave: (String) -> Unit,
) {
    var text by remember(value) { mutableStateOf(TextFieldValue(value)) }

    BasicAlertDialog(onDismissRequest = onDismiss) {
        Surface(
            color = MaterialTheme.colorScheme.surfaceContainerHigh,
            shadowElevation = 6.dp,
            shape = MaterialTheme.shapes.extraLarge,
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ProvideTextStyle(
                    MaterialTheme.typography.headlineSmall.copy(textAlign = TextAlign.Center)
                ) {
                    title()
                }

                if (description != null) {
                    Spacer(Modifier.height(16.dp))

                    ProvideTextStyle(
                        MaterialTheme.typography.bodyMedium.copy(textAlign = TextAlign.Center)
                    ) {
                        description()
                    }
                }

                Spacer(Modifier.height(16.dp))

                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = text,
                    onValueChange = { field ->
                        text = field
                    }
                )

                Spacer(Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    TextButton(onClick = onDismiss) {
                        Text("Cancel")
                    }

                    TextButton(onClick = { onSave(text.text) }) {
                        Text("Save")
                    }
                }
            }

        }
    }
}

@Preview
@Composable
private fun InputDialogPreview() {
    DroidAITheme {
        InputDialog(
            title = {
                Text("Update Ollama URL")
            },
            description = {
                Text("Set your Ollama URL to connect with the server. Remember to expose it as 0.0.0.0 when necessary")
            },
            value = "http://localhost:11434",
            onDismiss = {},
            onSave = {},
        )
    }
}
