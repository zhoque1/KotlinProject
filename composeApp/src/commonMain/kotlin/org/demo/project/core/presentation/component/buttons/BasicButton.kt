package org.demo.project.core.presentation.component.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.ic_near_me


import org.demo.project.core.presentation.component.NoRippleInteractionSource
import org.demo.project.core.presentation.theme.EerieBlack
import org.demo.project.core.presentation.theme.PhilippineSilver
import org.demo.project.core.presentation.theme.body_14_600_20
import org.jetbrains.compose.resources.painterResource

@Composable
fun BasicButton(
    surfaceModifier: Modifier = Modifier,
    textModifier: Modifier = Modifier,
    text: String,
    onClickAction: () -> Unit
) {
    Surface(
        modifier = surfaceModifier.padding(end = 8.dp),
        shape = RoundedCornerShape(50.dp),
        border = BorderStroke(1.dp, PhilippineSilver),
        onClick = {
            onClickAction()
        }
    ) {
        Text(
            modifier = textModifier.padding(horizontal = 12.dp, vertical = 8.dp),
            text = text,
            color = EerieBlack,
            style = MaterialTheme.typography.body_14_600_20(),
        )
    }
}