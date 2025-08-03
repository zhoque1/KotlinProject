package org.demo.project

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.all_filters__none_selected
import kotlinproject.composeapp.generated.resources.ic_clear
import org.jetbrains.compose.resources.painterResource
import org.demo.project.core.presentation.TopSearchBar
import org.demo.project.core.presentation.component.NoRippleInteractionSource
import org.demo.project.core.presentation.component.buttons.BasicButton
import org.demo.project.core.presentation.theme.BostonRed
import org.demo.project.core.presentation.theme.EerieBlack
import org.demo.project.core.presentation.theme.PhilippineSilver
import org.demo.project.core.presentation.theme.body_14_600_20


@Preview
@Composable
private fun FullScreenPreview(
    modifier: Modifier = Modifier,
    suggestion: String = "Enter a Location",
    haveLocation: Boolean = true,
    searchNearby: () -> Unit = {},
    onCloseSheet: () -> Unit = {}
){
    val selectedQuery = remember { mutableStateOf("") }
    val badgeCount: Int? = 3
    var showBadge by remember { mutableStateOf(false) }

    Surface(
        modifier = modifier
            .fillMaxSize(),
    ){
//        Column(
//            modifier = modifier
//                .fillMaxWidth()
//        ){
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth(),
//                horizontalArrangement = Arrangement.SpaceBetween,
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Spacer(modifier = Modifier.size(24.dp))
//                Text(
//                    text = "Location",
//                    style = MaterialTheme.typography.header_18_500_28(),
//                )
//
//                IconButton(
//                    modifier = Modifier,
//                    onClick = onCloseSheet
//                ) {
//                    Image(
//                        painter = painterResource(Res.drawable.x_close_exit),
//                        contentDescription = "Close Sheet"
//                    )
//                }
//            }
//            HorizontalDivider(
//                modifier = Modifier,
//                thickness = 1.dp,
//                color = PaleGray
//            )
//        }
        Column(
            modifier = Modifier.fillMaxSize(),
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 8.dp, bottom = 16.dp)
                ,
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ){
                TopSearchBar(
                    modifier = Modifier
                        .fillMaxWidth(0.835f),
                    suggestion = if(selectedQuery.value.isEmpty()) "Enter a location" else selectedQuery.value,
                    openTypeAhead = {
                    }
                ){
                    if (selectedQuery.value.isNotEmpty()) {
                        Icon(
                            painter = painterResource(Res.drawable.ic_clear),
                            contentDescription = "",
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(24.dp)
                                .clickable {
//                                        onClearActionClick()
                                    selectedQuery.value = ""
                                }
                        )
                    }
                }
                BadgedBox(
                    badge = {
                        if(showBadge){
                            if(badgeCount != null) {
                                Badge {
                                    Text(text = badgeCount.toString())
                                }
                            } else {
                                Badge()
                            }
                        }
                    }
                ) {
                    Icon(
                        modifier = Modifier
                            .clickable(
                                interactionSource = remember { NoRippleInteractionSource() },
                                indication = null
                            ) {
                                showBadge = !showBadge
                            },
                        painter = painterResource(Res.drawable.all_filters__none_selected),
                        tint = if (showBadge) {BostonRed} else PhilippineSilver,
                        contentDescription = null
                    )
                }
            }

            Row(
                modifier = Modifier,
//                verticalArrangement = Arrangement.Center,
//                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                BasicButton(
                    surfaceModifier = Modifier
                        .padding(start = 8.dp),
                    text = "For Lease",
                    onClickAction = {
                    }
                )

                BasicButton(
                    text = "Space Uses",
                    onClickAction = {
                    }
                )

                BasicButton(
                    text = "Rent",
                    onClickAction = {
                    }
                )

                BasicButton(
                    text = "Size",
                    onClickAction = {
                    }
                )
            }
        }

    }
}


