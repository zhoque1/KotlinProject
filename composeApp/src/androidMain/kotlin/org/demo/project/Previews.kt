package org.demo.project

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.go_back
import kotlinproject.composeapp.generated.resources.ic_back
import org.demo.project.core.presentation.component.BodyText
import org.demo.project.core.presentation.component.NoRippleInteractionSource
import org.demo.project.core.presentation.component.buttons.SearchNearbyButton
import org.demo.project.core.presentation.theme.DesertWhite
import org.demo.project.features.books.presentation.book_list.components.BookSearchBar
import org.demo.project.features.presentation.screens.QuerySearch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Preview
@Composable
private fun BookSearchBarPreview() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        BookSearchBar(
            searchQuery = "",
            onSearchQueryChange = {},
            onImeSearch = {},
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Preview
@Composable
private fun TopSearchBarPreview(
    modifier: Modifier = Modifier,
    suggestion: String = "Enter a Location",
    haveLocation: Boolean = true,
    openTypeAhead: () -> Unit = {},
    searchNearby: () -> Unit = {},
) {
//    TopSearchBar(
//        modifier = Modifier,
//        suggestion = "Enter a Location",
//        haveLocation= false,
//        openTypeAhead = {},
//        searchNearby = {},
//    )
    Surface(
        shape = RoundedCornerShape(30.dp),
        modifier = modifier
            .height(40.dp),
        border = BorderStroke(1.dp, Color.Gray)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .clickable(
                    interactionSource = remember { NoRippleInteractionSource() },
                    indication = null
                ) { openTypeAhead() },
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
                        .fillMaxWidth(0.9f)
                        .padding(start = 8.dp),
                    title = suggestion,
                    textStyle = MaterialTheme.typography.bodyMedium,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
private fun AutoCompleteScreenPreview(
    modifier: Modifier = Modifier,
    suggestion: String = "Enter a Location",
    haveLocation: Boolean = true,
    searchNearby: () -> Unit = {},
    onCloseSheet: () -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = DesertWhite)
            .minimumInteractiveComponentSize()
    ){
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                modifier = Modifier,
                onClick = onCloseSheet
            ) {
                Image(
                    painter = painterResource(Res.drawable.ic_back),
                    colorFilter = ColorFilter.tint(Color.Gray),
                    contentDescription = stringResource(Res.string.go_back)
                )
            }
            SearchNearbyButton(
                haveLocation = haveLocation,
                searchNearby = searchNearby
            )
//            BodyText(
//                modifier = Modifier
//                    .fillMaxWidth(0.9f)
//                    .padding(start = 8.dp),
//                title = suggestion,
//                textStyle = MaterialTheme.typography.bodyMedium,
//                maxLines = 1
//            )
//            AutoComplete()
            QuerySearch()
        }

    }
}

//@Composable
//fun AutoComplete() {
//
//    val states = listOf(
//        "Alabama",
//        "Alaska",
//        "Arizona",
//        "Arkansas",
//        "California",
//        "Colorado",
//        "Connecticut",
//        "Delaware",
//        "Florida",
//        "Georgia",
//        "Hawaii",
//        "Idaho",
//        "Illinois",
//        "Indiana",
//        "Iowa",
//        "Kansas",
//        "Kentucky",
//        "Louisiana",
//        "Maine",
//        "Maryland",
//        "Massachusetts",
//        "Michigan",
//        "Minnesota",
//        "Mississippi",
//        "Missouri",
//        "Montana",
//        "Nebraska",
//        "Nevada",
//        "New Hampshire",
//        "New Jersey",
//        "New Mexico",
//        "New York",
//        "North Carolina",
//        "North Dakota",
//        "Ohio",
//        "Oklahoma",
//        "Oregon",
//        "Pennsylvania",
//        "Rhode Island",
//        "South Carolina",
//        "South Dakota",
//        "Tennessee",
//        "Texas",
//        "Utah",
//        "Vermont",
//        "Virginia",
//        "Washington",
//        "West Virginia",
//        "Wisconsin",
//        "Wyoming"
//    )
//
//
//    var category by remember {
//        mutableStateOf("")
//    }
//
//    val heightTextFields by remember {
//        mutableStateOf(55.dp)
//    }
//
//    var textFieldSize by remember {
//        mutableStateOf(Size.Zero)
//    }
//
//    var expanded by remember {
//        mutableStateOf(false)
//    }
//    val interactionSource = remember {
//        MutableInteractionSource()
//    }
//
//    // Category Field
//    Column(
//        modifier = Modifier
////            .padding(30.dp)
//            .fillMaxWidth()
//            .clickable(
//                interactionSource = interactionSource,
//                indication = null,
//                onClick = {
//                    expanded = false
//                }
//            )
//    ) {
//
//        Column(modifier = Modifier.fillMaxWidth()) {
//
//            Row(modifier = Modifier.fillMaxWidth()) {
//                TextField(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .height(heightTextFields)
////                        .border(
////                            width = 1.8.dp,
////                            color = Color.Black,
////                            shape = RoundedCornerShape(15.dp)
////                        )
//                        .onGloballyPositioned { coordinates ->
//                            textFieldSize = coordinates.size.toSize()
//                        },
//                    value = category,
//                    onValueChange = {
//                        category = it
//                        expanded = true
//                    },
//                    placeholder = { Text("Enter a location") },
//                    colors = TextFieldDefaults.colors(
//                        focusedContainerColor = Color.Transparent,
//                        unfocusedContainerColor = Color.Transparent,
//                        disabledContainerColor = Color.Transparent, // Optional: if you have a disabled state
//                        focusedIndicatorColor = Color.Transparent,
//                        unfocusedIndicatorColor = Color.Transparent,
//                        disabledIndicatorColor = Color.Transparent, // Optional: if you have a disabled state
//                        cursorColor = Color.Black
//                    ),
//                    textStyle = TextStyle(
//                        color = Color.Black,
//                        fontSize = 16.sp
//                    ),
//                    keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.Text,
//                        imeAction = ImeAction.Done
//                    ),
//                    singleLine = true,
////                    trailingIcon = {
////                        IconButton(onClick = { expanded = !expanded }) {
////                            Icon(
////                                modifier = Modifier.size(24.dp),
////                                imageVector = Icons.Rounded.KeyboardArrowDown,
////                                contentDescription = "arrow",
////                                tint = Color.Black
////                            )
////                        }
////                    }
//                )
//            }
//
//            AnimatedVisibility(visible = expanded) {
//                Card(
//                    modifier = Modifier
//                        .padding(horizontal = 5.dp)
//                        .width(textFieldSize.width.dp),
//                    shape = RoundedCornerShape(10.dp)
//                ) {
//
//                    LazyColumn(
//                        modifier = Modifier.heightIn(max = 150.dp),
//                    ) {
//
//                        if (category.isNotEmpty()) {
//                            items(
//                                states.filter {
//                                    it.lowercase()
//                                        .contains(category.lowercase()) || it.lowercase()
//                                        .contains("others")
//                                }
//                                    .sorted()
//                            ) {
//                                ItemsCategory(title = it) { title ->
//                                    category = title
//                                    expanded = false
//                                }
//                            }
//                        } else {
//                            items(
//                                states.sorted()
//                            ) {
//                                ItemsCategory(title = it) { title ->
//                                    category = title
//                                    expanded = false
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//    }
//}
//
//
//
//@Composable
//fun ItemsCategory(
//    title: String,
//    onSelect: (String) -> Unit
//) {
//
//    Row(
//        modifier = Modifier
//            .fillMaxWidth()
//            .clickable {
//                onSelect(title)
//            }
//            .padding(10.dp)
//    ) {
//        Text(text = title, fontSize = 16.sp)
//    }
//
//}

//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun QuerySearch(
//    modifier: Modifier = Modifier,
//    query: String = "",
//    screenType: String = "Enter a Location",
//    colors: TextFieldColors? = null,
//    onDoneActionClick: () -> Unit = {},
//    onClearActionClick: () -> Unit = {},
//    onQueryChanged: (String) -> Unit = {}
//) {
//    val focusRequester = FocusRequester()
//    val focusManager = LocalFocusManager.current
//    Row(
//        modifier = Modifier
//            .wrapContentHeight()
//            .fillMaxWidth(),
//        verticalAlignment = Alignment.CenterVertically
//    ) {
//        OutlinedTextField(
//            modifier = modifier
//                .weight(1f)
//                .wrapContentHeight()
//                .focusRequester(focusRequester),
//            value = query,
//            placeholder = {
//                Text(
//                    text = screenType,
//                    style = MaterialTheme.typography.labelSmall.copy(
//                        color = SilverChalice
//                    ),
//                    overflow = TextOverflow.Ellipsis,
//                    maxLines = 1
//                )
//            },
//            onValueChange = {
//                val input = it.filter { filter ->
//                    !filter.isSurrogate()
//                }
//                onQueryChanged(input)
//            },
//            textStyle = MaterialTheme.typography.labelSmall.copy(
//                lineHeight = 27.2.sp,
//                letterSpacing = 0.45.sp,
//                color = Black
//            ),
//            singleLine = true,
//            keyboardActions = KeyboardActions(
//                onSearch = {
//                    focusManager.clearFocus()
//                    onDoneActionClick()
//                }
//            ),
//            keyboardOptions = KeyboardOptions(
//                imeAction = ImeAction.Search,
//                keyboardType = KeyboardType.Text
//            ),
//            colors = colors ?: outlinedTextFieldColors(
//                focusedBorderColor = Color.Transparent,
//                unfocusedBorderColor = Color.Transparent
//            )
//        )
//        if (query.isNotEmpty()) {
//            Icon(
//                painter = painterResource(Res.drawable.ic_clear),
//                contentDescription = "",
//                modifier = Modifier
//                    .padding(start = 8.dp)
//                    .size(24.dp)
//                    .clickable {
//                        onClearActionClick()
//                    }
//            )
//        }
//    }
//
//    DisposableEffect(Unit) {
//        focusRequester.requestFocus()
//        onDispose { }
//    }
//}

//private val books = (1..100).map {
//    Book(
//        id = it.toString(),
//        title = "Book $it",
//        imageUrl = "https://test.com",
//        authors = listOf("Philipp Lackner"),
//        description = "Description $it",
//        languages = emptyList(),
//        firstPublishYear = null,
//        averageRating = 4.67854,
//        ratingCount = 5,
//        numPages = 100,
//        numEditions = 3
//    )
//}
//
//@Preview
//@Composable
//private fun BookListScreenPreview() {
//    BookListScreen(
//        state = BookListState(
//            searchResults = books
//        ),
//        onAction = {}
//    )
//}