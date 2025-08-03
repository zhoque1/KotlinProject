package org.demo.project.core.presentation.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource


sealed interface UiText {
    data class DynamicString(val value: String): UiText
    class StringResourceId(
        val id: StringResource,
        val args: Array<Any> = arrayOf()
    ): UiText

    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResourceId -> stringResource(resource = id, formatArgs = args)
        }
    }
}


@Composable
fun ScreenTitleText(
    title: String,
    modifier: Modifier = Modifier,
    rowModifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.titleMedium,
    overflowOption: TextOverflow = TextOverflow.Clip,
    maxLines: Int = Int.MAX_VALUE,
    badge: @Composable (() -> Unit)? = null,
) {
    if (badge == null) {
        Text(
            text = title,
            style = textStyle,
            modifier = modifier,
            overflow = overflowOption,
            maxLines = maxLines
        )
    } else {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = rowModifier
        ) {
            Text(
                text = title,
                style = textStyle,
                modifier = modifier,
                overflow = overflowOption,
                maxLines = maxLines
            )
            badge()
        }
    }
}

@Composable
fun LabelText(
    title: String,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 14.sp,
    color: Color = Black
) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelMedium.copy(
            fontSize = fontSize,
            lineHeight = 21.sp
        ),
        modifier = modifier.fillMaxWidth(),
        color = color,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.W600,
    )
}

@Composable
fun BodyText(
    title: String,
    modifier: Modifier = Modifier,
    textAlign: TextAlign? = null,
    textStyle: TextStyle = MaterialTheme.typography.bodyMedium,
    overflowOption: TextOverflow = TextOverflow.Ellipsis,
    maxLines: Int? = null,
    textDecoration: TextDecoration? = null
) {
    Text(
        text = title,
        textAlign = textAlign,
        style = textStyle,
        modifier = modifier,
        overflow = overflowOption,
        maxLines = maxLines ?: Int.MAX_VALUE,
        textDecoration = textDecoration
    )
}