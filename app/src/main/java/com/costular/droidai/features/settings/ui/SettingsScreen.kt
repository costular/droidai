package com.costular.droidai.features.settings.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Computer
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.costular.droidai.core.ui.dialog.InputDialog
import com.costular.droidai.features.settings.model.OllamaUrl
import com.costular.droidai.features.settings.ui.components.SettingOption
import com.costular.droidai.features.settings.ui.components.SettingSection
import com.costular.droidai.ui.theme.DroidAITheme

@Composable
fun SettingsScreen(
    onNavigateUp: () -> Unit,
    viewModel: SettingsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val showEditOllamaUrl by viewModel.showEditOllamaUrl.collectAsStateWithLifecycle()

    SettingsScreenContent(
        uiState = uiState,
        showEditOllamaUrl = showEditOllamaUrl,
        onNavigateUp = onNavigateUp,
        onEditOllamaUrl = viewModel::onEditOllamaUrl,
        onDismissEditOllamaUrl = viewModel::dismissEditOllamaUrl,
        onUpdateOllamaUrl = viewModel::updateOllamaUrl,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenContent(
    uiState: SettingsUiState,
    showEditOllamaUrl: Boolean,
    onEditOllamaUrl: () -> Unit,
    onDismissEditOllamaUrl: () -> Unit,
    onUpdateOllamaUrl: (String) -> Unit,
    onNavigateUp: () -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        "Settings",
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onNavigateUp) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back"
                        )
                    }
                },
            )
        }
    ) { padding ->
        when (uiState) {
            is SettingsUiState.Success -> SettingsSuccess(padding, uiState, onEditOllamaUrl)
            is SettingsUiState.Loading -> SettingsLoading()
            is SettingsUiState.Failure -> Unit // TODO: Add the failure
        }

        if (showEditOllamaUrl && uiState is SettingsUiState.Success) {
            InputDialog(
                title = {
                    Text("Set Ollama server URL")
                },
                description = {
                    val text = buildAnnotatedString {
                        append("Enter the Ollama server's URL to connect with it ")
                        append("(e.g. ")
                        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
                            append("http://192.168.1.100:11434")
                        }
                        append(")")
                        append("\n\n")
                        append("Remember to set ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("OLLAMA_HOST")
                        }
                        append(" environment variable to ")
                        withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("0.0.0.0")
                        }
                    }
                    Text(text = text)
                },
                value = uiState.ollamaUrl?.url ?: "",
                onDismiss = onDismissEditOllamaUrl,
                onSave = onUpdateOllamaUrl,
            )
        }
    }
}

@Composable
fun SettingsSuccess(
    padding: PaddingValues,
    uiState: SettingsUiState.Success,
    onEditOllamaUrl: () -> Unit,
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(
            top = 16.dp + padding.calculateTopPadding(),
            bottom = 16.dp + padding.calculateBottomPadding(),
        )
    ) {
        item {
            ServerSection(
                ollamaUrl = uiState.ollamaUrl,
                onEditOllamaUrl = onEditOllamaUrl,
            )
        }
    }
}

@Composable
fun SettingsLoading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun ServerSection(
    ollamaUrl: OllamaUrl?,
    onEditOllamaUrl: () -> Unit,
) {
    SettingSection(title = "Server") {
        SettingOption(
            title = "Ollama server URL",
            option = ollamaUrl?.url ?: "",
            icon = Icons.Outlined.Computer,
            onClick = onEditOllamaUrl,
        )
    }
}

@Preview
@Composable
private fun SettingsScreenSuccessPreview() {
    DroidAITheme {
        SettingsScreenContent(
            uiState = SettingsUiState.Success(
                ollamaUrl = OllamaUrl("http://localhost:1334")
            ),
            onNavigateUp = {},
            showEditOllamaUrl = false,
            onDismissEditOllamaUrl = {},
            onEditOllamaUrl = {},
            onUpdateOllamaUrl = {},
        )
    }
}

@Preview
@Composable
private fun SettingsScreenLoadingPreview() {
    DroidAITheme {
        SettingsScreenContent(
            uiState = SettingsUiState.Loading,
            onNavigateUp = {},
            showEditOllamaUrl = false,
            onDismissEditOllamaUrl = {},
            onEditOllamaUrl = {},
            onUpdateOllamaUrl = {},
        )
    }
}
