package com.costular.droidai.features.chat.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Stop
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.costular.droidai.R
import com.costular.droidai.common.models.model.Model
import com.costular.droidai.features.chat.model.Message
import com.costular.droidai.features.chat.model.MessageRole
import com.costular.droidai.ui.theme.DroidAITheme
import com.costular.droidai.util.LifecycleObserver
import java.time.Instant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ChatScreen(
    viewModel: ChatViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LifecycleObserver { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            viewModel.loadModels()
        }
    }

    ChatScreen(
        state = uiState,
        onValueChange = viewModel::onInput,
        onSubmit = viewModel::onSubmit,
        onStopGenerating = viewModel::onStopGenerating,
        onShowPickModel = viewModel::openModelPicker,
        onDismissPickModel = viewModel::dismissModelPicker,
        onPickModel = viewModel::onPickModel
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(
    state: ChatUIState,
    onValueChange: (TextFieldValue) -> Unit,
    onSubmit: () -> Unit,
    onStopGenerating: () -> Unit,
    onShowPickModel: () -> Unit,
    onDismissPickModel: () -> Unit,
    onPickModel: (Model) -> Unit,
) {
    val coroutineScope = rememberCoroutineScope()

    if (state.showModelPicker && state.selectedModel != null) {
        ModelPickerBottomSheet(
            onPickModel = onPickModel,
            onDismiss = onDismissPickModel,
            models = state.availableModels,
            selectedModel = state.selectedModel,
        )
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface,
                ),
                title = {
                    state.selectedModel?.let { model ->
                        SuggestionChip(
                            onClick = onShowPickModel,
                            label = {
                                Text(model.name)
                            },
                            icon = {
                                Icon(
                                    imageVector = Icons.Default.ArrowDropDown,
                                    contentDescription = null,
                                )
                            },
                        )
                    }
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(
                            imageVector = Icons.Filled.Add,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        },
    ) { padding ->
        ChatScreenContent(
            padding = padding,
            state = state,
            onValueChange = onValueChange,
            coroutineScope = coroutineScope,
            onSubmit = onSubmit,
            onStopGenerating = onStopGenerating,
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun ChatScreenContent(
    lazyListState: LazyListState = rememberLazyListState(),
    padding: PaddingValues,
    state: ChatUIState,
    onValueChange: (TextFieldValue) -> Unit,
    coroutineScope: CoroutineScope,
    onSubmit: () -> Unit,
    onStopGenerating: () -> Unit
) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            when {
                state.isErrorConnection -> ConnectionError()
                else -> MessageList(lazyListState, padding, state)
            }

            UserInput(
                generating = state.isGenerating,
                text = state.userInput,
                onValueChange = onValueChange,
                onSubmit = {
                    coroutineScope.launch {
                        lazyListState.scrollToItem(0)
                    }
                    onSubmit()
                },
                onStopGenerating = onStopGenerating,
            )
        }
    }
}

@Composable
private fun ColumnScope.ConnectionError() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Text(
            text = "Connection error",
            style = MaterialTheme.typography.headlineSmall,
        )

        Spacer(Modifier.height(8.dp))

        Text(
            text = "It was impossible to connect with Ollama. Did you forget to set the URL? Try to update it on Settings",
            style = MaterialTheme.typography.bodyLarge,
        )

        Spacer(Modifier.height(16.dp))

        Button(onClick = { /*TODO*/ }) {
            Text("Open Settings")
        }
    }
}

@Composable
@OptIn(ExperimentalFoundationApi::class)
private fun ColumnScope.MessageList(
    lazyListState: LazyListState,
    padding: PaddingValues,
    state: ChatUIState
) {
    LazyColumn(
        state = lazyListState,
        modifier = Modifier.Companion.weight(1f),
        contentPadding = PaddingValues(
            start = 16.dp,
            end = 16.dp,
            bottom = 16.dp,
            top = 8.dp + padding.calculateTopPadding(),
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        reverseLayout = true,
    ) {
        if (state.isGenerating) {
            item("generating") {
                GenerationAnimation()
            }
        }

        items(state.messages.asReversed()) {
            MessageItem(
                content = it.content,
                role = it.role,
                model = "AI",
                modifier = Modifier.animateItemPlacement(),
            )
        }
    }
}

@Composable
private fun MessageItem(
    model: String,
    content: String,
    role: MessageRole,
    modifier: Modifier = Modifier,
) {
    val colors = if (role == MessageRole.User) {
        CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
            contentColor = MaterialTheme.colorScheme.onSurface,
        )
    } else {
        CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
        )
    }

    val roleLabel = when (role) {
        MessageRole.User -> "You" // TODO: change this
        MessageRole.Assistant -> model
        MessageRole.System -> "System"
    }

    Card(
        modifier = modifier.fillMaxWidth(),
        colors = colors,
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = roleLabel,
                style = MaterialTheme.typography.labelLarge,
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = content,
                style = MaterialTheme.typography.bodyLarge,
            )
        }
    }
}

@Composable
private fun UserInput(
    generating: Boolean,
    text: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    onSubmit: () -> Unit,
    onStopGenerating: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier,
        color = MaterialTheme.colorScheme.surface,
        contentColor = MaterialTheme.colorScheme.onSurface,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                OutlinedTextField(
                    value = text,
                    onValueChange = onValueChange,
                    modifier = Modifier.weight(1f),
                    maxLines = 5,
                    shape = RoundedCornerShape(percent = 20),
                    enabled = !generating,
                    placeholder = {
                        Text("Write a message")
                    }
                )

                Spacer(Modifier.width(8.dp))

                if (generating) {
                    IconButton(
                        onClick = onStopGenerating,
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Stop,
                            contentDescription = null // TODO:
                        )
                    }
                } else {
                    IconButton(
                        onClick = onSubmit,
                        colors = IconButtonDefaults.iconButtonColors(
                            containerColor = MaterialTheme.colorScheme.primary,
                            contentColor = MaterialTheme.colorScheme.onPrimary,
                        ),
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Outlined.Send,
                            contentDescription = null // TODO:
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun GenerationAnimation(
    modifier: Modifier = Modifier,
) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.bubbles))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever,
    )

    LottieAnimation(
        modifier = modifier,
        composition = composition,
        progress = { progress },
    )
}

@Preview
@Composable
private fun ChatScreenPreview() {
    DroidAITheme {
        ChatScreen(
            state = ChatUIState(
                messages = listOf(
                    Message(
                        content = "What's the best tennis player ever?",
                        role = MessageRole.User
                    ),
                    Message(
                        content = "There's no single best tennis player ever, as the debate is highly subjective and depends on what factors you prioritize.",
                        role = MessageRole.Assistant,
                    )
                ),
                userInput = TextFieldValue("Who is better? Nadal or Alcaraz?"),
                isGenerating = false,
                selectedModel = Model(
                    name = "Llama2:latest",
                    modifiedAt = Instant.now(),
                    size = 100000L,
                    digest = "apokdwapoada09di32",
                    parameterSize = "7B",
                    quantizationLevel = "Q4_0",
                )
            ),
            onValueChange = {},
            onSubmit = {},
            onStopGenerating = {},
            onShowPickModel = {},
            onDismissPickModel = {},
            onPickModel = {},
        )
    }
}
