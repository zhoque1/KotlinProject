package org.demo.project.core.presentation.component.buttons

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_near_me


import org.demo.project.core.presentation.component.NoRippleInteractionSource
import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchNearbyButton(
    modifier: Modifier = Modifier,
    haveLocation: Boolean,
    searchNearby: () -> Unit
) {
    if (haveLocation) {
        Icon(
            modifier = modifier
                .clickable(
                    interactionSource = remember { NoRippleInteractionSource() },
                    indication = null
                ) {
                    searchNearby()
                },
            painter = painterResource(Res.drawable.ic_near_me),
            tint = Color.Black,
            contentDescription = null
        )
    }
}