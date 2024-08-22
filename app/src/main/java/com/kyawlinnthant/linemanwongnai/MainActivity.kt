package com.kyawlinnthant.linemanwongnai

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.kyawlinnthant.presentation.CoinsScreen
import com.kyawlinnthant.theme.LineManWongNaiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LineManWongNaiTheme {
                CoinsScreen()
            }
        }
    }
}
