package com.costular.droidai.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.costular.droidai.ui.theme.DroidAITheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DroidAITheme {
                val navHostController = rememberNavController()

                Home(
                    navController = navHostController,
                    modifier = Modifier.fillMaxSize(),
                )
            }
        }
    }
}
