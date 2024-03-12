package com.costular.droidai.features.chat.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.RadioButton
import androidx.compose.material3.SheetState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.costular.droidai.features.chat.model.Model
import com.costular.droidai.ui.theme.DroidAITheme
import java.time.Instant

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelPickerBottomSheet(
    onPickModel: (Model) -> Unit,
    selectedModel: Model,
    models: List<Model>,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
    sheetState: SheetState = rememberModalBottomSheetState(),
) {
    ModalBottomSheet(
        sheetState = sheetState,
        onDismissRequest = onDismiss,
    ) {
        ModalPickerContent(
            models = models,
            selectedModel = selectedModel,
            onPickModel = onPickModel,
            modifier = modifier.fillMaxSize(),
        )
    }
}

@Composable
private fun ColumnScope.ModalPickerContent(
    selectedModel: Model,
    models: List<Model>,
    onPickModel: (Model) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(modifier = modifier) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 16.dp),
        ) {
            item(key = "header") {
                Text(
                    text = "Select the model to use", // TODO: translate
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            item {
                Spacer(Modifier.height(16.dp))
            }

            itemsIndexed(models) { index, model ->
                ModalItem(
                    model = model,
                    isSelected = selectedModel == model,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onPickModel(model)
                    }
                )

                if (index < models.lastIndex) {
                    HorizontalDivider(modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}

@Composable
fun ModalItem(
    model: Model,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ListItem(
        modifier = modifier.clickable(onClick = onClick),
        headlineContent = {
            Text(
                text = model.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        supportingContent = {
            Row {
                Text(
                    text = model.id,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                Spacer(Modifier.width(4.dp))

                Text(
                    text = "·",
                    fontWeight = FontWeight.ExtraBold,
                )

                Spacer(Modifier.width(4.dp))

                Text(
                    text = model.sizeHumanReadable,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        },
        trailingContent = {
            Text(
                text = model.editedHumanReadable,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        },
        leadingContent = {
            RadioButton(
                selected = isSelected,
                onClick = onClick,
            )
        }
    )
}

@Preview
@Composable
private fun ModelPickerBottomSheetPreview() {
    DroidAITheme {
        Column(modifier = Modifier.fillMaxSize()) {
            ModalPickerContent(
                models = listOf(
                    Model(
                        name = "Llama2:latest",
                        modifiedAt = Instant.now(),
                        size = 100000L,
                        digest = "78e26419b446adaiwodj092u1293uawd",
                        parameterSize = "7B",
                        quantizationLevel = "Q4_0",
                    ),
                    Model(
                        name = "Llama2:latest",
                        modifiedAt = Instant.now().minusSeconds(3600),
                        size = 100000L,
                        digest = "78e26419b446adaiwodj092u1293uawd",
                        parameterSize = "7B",
                        quantizationLevel = "Q4_0",
                    ),
                ),
                selectedModel = Model(
                    name = "Llama2:latest",
                    modifiedAt = Instant.now(),
                    size = 100000L,
                    digest = "78e26419b446adaiwodj092u1293uawd",
                    parameterSize = "7B",
                    quantizationLevel = "Q4_0",
                ),
                onPickModel = {},
            )
        }
    }
}
