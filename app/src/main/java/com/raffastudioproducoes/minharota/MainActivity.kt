package com.raffastudioproducoes.minharota

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.raffastudioproducoes.minharota.ui.MainAppContent
import com.raffastudioproducoes.minharota.ui.theme.MinhaRotaTema

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MinhaRotaTema {
                MainAppContent()
            }
        }
    }
}
