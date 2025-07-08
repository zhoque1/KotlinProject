package org.demo.project.core.presentation.component

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
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