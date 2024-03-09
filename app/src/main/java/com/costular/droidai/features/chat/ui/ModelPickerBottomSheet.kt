package com.costular.droidai.features.chat.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.costular.droidai.features.chat.model.Model

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModelPickerBottomSheet(
    onPickModel: (Model) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier,
) {
    ModalBottomSheet(
        modifier = modifier,
        onDismissRequest = onDismiss,
    ) {
        Text("Hello, here we'll show the models list :)")
    }
}
