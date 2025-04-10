package org.demo.project

import androidx.compose.ui.window.ComposeUIViewController
import org.demo.project.App
import org.demo.project.di.initKoin



fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }