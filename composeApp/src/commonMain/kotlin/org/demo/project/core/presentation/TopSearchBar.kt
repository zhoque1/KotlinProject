package org.demo.project.core.presentation

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.demo.project.core.presentation.component.BodyText
import org.demo.project.core.presentation.component.NoRippleInteractionSource
import org.demo.project.core.presentation.component.buttons.SearchNearbyButton
import org.demo.project.core.presentation.theme.PhilippineSilver

@Composable
fun TopSearchBar(
    modifier: Modifier = Modifier,
    suggestion: String = "",
    haveLocation: Boolean = true,
    openTypeAhead: () -> Unit = {},
    searchNearby: () -> Unit = {},
    showClearIcon: @Composable () -> Unit = {},
) {
    Surface(
        shape = RoundedCornerShape(30.dp),
        modifier = modifier
            .height(40.dp),
        border = BorderStroke(1.dp, PhilippineSilver)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 12.dp)
                .clickable(
                    onClick = {
                        openTypeAhead()
                        println("Search Nearby")
                    },
                    interactionSource = remember { NoRippleInteractionSource() },
                    indication = null
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                SearchNearbyButton(
                    haveLocation = haveLocation,
                    searchNearby = searchNearby
                )
                BodyText(
                    modifier = Modifier
                        .weight(0.8f)
                        .padding(start = 8.dp),
                    title = suggestion,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )
                showClearIcon()
            }
        }
    }
}