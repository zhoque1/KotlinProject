package org.demo.project.features.presentation.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.BottomSheetDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuDefaults.outlinedTextFieldColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SheetValue
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import androidx.navigation.NavHostController
import kotlinproject.composeapp.generated.resources.Res
import kotlinproject.composeapp.generated.resources.go_back
import kotlinproject.composeapp.generated.resources.ic_back
import kotlinproject.composeapp.generated.resources.ic_clear
import kotlinproject.composeapp.generated.resources.x_close_exit
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import org.demo.project.core.presentation.component.TopSearchBar
import org.demo.project.core.presentation.component.buttons.SearchNearbyButton
import org.demo.project.core.presentation.theme.DesertWhite
import org.demo.project.core.presentation.theme.PaleGray
import org.demo.project.core.presentation.theme.SilverChalice
import org.demo.project.core.presentation.theme.headerLarge
import org.demo.project.core.presentation.theme.header_18_500_28
import org.demo.project.features.navigation.Routes
import org.demo.project.features.navigation.Scaffold1Screen
import org.demo.project.features.posts.presentation.screen.PostsContent
import org.demo.project.features.posts.presentation.viewModel.PostListState
import org.demo.project.features.posts.presentation.viewModel.PostsEvent
import org.demo.project.features.posts.presentation.viewModel.PostsViewModel
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavHostController) {
    val postsViewModel = koinViewModel<PostsViewModel>()
    val state = postsViewModel.uiState.value


    // 1. State to control the visibility of the bottom sheet from your screen's logic
    var showBottomSheet by remember { mutableStateOf(false) }

    // 2. Remember the sheet state
    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = true, // Or false, depending on your needs
        confirmValueChange = { targetState ->
            // This is the key part:
            // If the sheet is trying to change to the 'Hidden' state,
            // we return 'false' to prevent that change.
            // This blocks swipe-down-to-close.
            if (targetState == SheetValue.Hidden) {
                false // Disallow changing to Hidden state via user swipe
            } else {
                true // Allow other state changes (e.g., to Expanded)
            }
        }
    )

    // 3. Remember a CoroutineScope to launch suspend functions for sheet control
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit){
        postsViewModel.postEvent(PostsEvent.OnGetPosts)
    }

    Scaffold1Screen(navController = navController){
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                TopSearchBar(
                    openTypeAhead = {
                        scope.launch {
                            showBottomSheet = true
                            sheetState.expand()
                        }
                    }
                )
                if (showBottomSheet) {
                    ModalBottomSheet(
                        modifier = Modifier
                            .fillMaxSize(),
                        dragHandle = {  },
                        onDismissRequest = {
                            // This is called when the user tries to dismiss the sheet,
                            // e.g., by tapping outside or swiping down.
//                            showBottomSheet = false
                            // You might not need to manually call sheetState.hide() here if onDismissRequest
                            // is sufficient for your logic to set showBottomSheet to false.
                            // However, if you want to ensure animation, you can do:
                            // scope.launch { sheetState.hide() }.invokeOnCompletion {
                            //     if (!sheetState.isVisible) {
                            //         showBottomSheet = false
                            //     }
                            // }
                        },
                        sheetState = sheetState,
                        //You can customize window insets, drag handle, etc.
                        contentWindowInsets = { WindowInsets.safeDrawing }, // Example: To remove all insets
                        //dragHandle = { BottomSheetDefaults.DragHandle() } // To add a default drag handle
                    ) {
                        // 5. Content of your Bottom Sheet
                        BottomSheetContent(
                            onCloseSheet = {
                                scope.launch {
                                    showBottomSheet = false
                                    sheetState.hide()
                                }
                            }
                        )
                    }
                }

                Text(
                    "Chat Screen",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.padding(vertical = 20.dp)
                )
                Button(onClick = { navController.navigate(Routes.About.route) }) {
                    Text(text = "About")
                }
                Button(onClick = { navController.navigate(Routes.HomeDetail.route) }) {
                    Text(text = "Navigate To Home Detail")
                }
                when{
                    state.isLoading ->{
                        println("IsLoading")
                        CircularProgressIndicator()
                    }
                }
                when(val postListState = state.postListState){
//                    is PostListState.IsLoading ->{
//                        println("IsLoading")
//                        CircularProgressIndicator()
//                    }
                    is PostListState.Error ->{
                        println("Error")
                        Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            textAlign = TextAlign.Center,
                            fontSize = 30.sp,
                            text = postListState.message,
                        )
                    }
                    is PostListState.PostsLoaded ->{
                        println("PostsLoaded")
                        PostsContent(
                            modifier = Modifier.fillMaxSize(),
                            list = postListState.posts,
                            onPostClick = {
                                // trying to send data through event and state change
                                postsViewModel.postEvent(PostsEvent.OnPostClick(post = it))

                                // trying to send data through route
                                val post = Json.encodeToString(it)
                                navController.navigate("post-detail_route/${post}")
                            }
                        )
                    }
                    else -> {
                        println("OnIdle")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetContent(
    onCloseSheet: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.size(24.dp))
            Text(
                text = "Location",
                style = MaterialTheme.typography.header_18_500_28(),
            )

            IconButton(
                modifier = Modifier,
                onClick = onCloseSheet
            ) {
                Image(
                    painter = painterResource(Res.drawable.x_close_exit),
                    contentDescription = "Close Sheet"
                )
            }
        }
        HorizontalDivider(
            modifier = Modifier,
            thickness = 1.dp,
            color = PaleGray
        )
        AutoCompleteScreenPreview(onCloseSheet = onCloseSheet)

        Text("This is the Modal Bottom Sheet", style = MaterialTheme.typography.headerLarge())
        Spacer(modifier = Modifier.height(16.dp))
        Text("You can put any composable content here.")
        Spacer(modifier = Modifier.height(24.dp))
        Button(onClick = onCloseSheet) {
            Text("Close Sheet")
        }
        // Add more content like lists, forms, etc.
        Spacer(modifier = Modifier.height(16.dp)) // Ensure some padding at the bottom
    }
}

@Composable
private fun AutoCompleteScreenPreview(
    modifier: Modifier = Modifier,
    suggestion: String = "Enter a Location",
    haveLocation: Boolean = true,
    searchNearby: () -> Unit = {},
    onCloseSheet: () -> Unit = {},
) {
    val query = remember { mutableStateOf("") }

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
            QuerySearch(
                query = query.value,
                screenType = "Location",
                onClearActionClick = {
                    query.value = ""
                },
                onDoneActionClick = onCloseSheet,
                onQueryChanged = { newQuery ->
                    query.value = newQuery

                    if (newQuery.length > 2) {
                        println(newQuery)
                    } else {
                        println("query is too short")
                    }
                }
            )
        }

    }
}

@Composable
fun AutoComplete() {

    val states = listOf(
        "Alabama",
        "Alaska",
        "Arizona",
        "Arkansas",
        "California",
        "Colorado",
        "Connecticut",
        "Delaware",
        "Florida",
        "Georgia",
        "Hawaii",
        "Idaho",
        "Illinois",
        "Indiana",
        "Iowa",
        "Kansas",
        "Kentucky",
        "Louisiana",
        "Maine",
        "Maryland",
        "Massachusetts",
        "Michigan",
        "Minnesota",
        "Mississippi",
        "Missouri",
        "Montana",
        "Nebraska",
        "Nevada",
        "New Hampshire",
        "New Jersey",
        "New Mexico",
        "New York",
        "North Carolina",
        "North Dakota",
        "Ohio",
        "Oklahoma",
        "Oregon",
        "Pennsylvania",
        "Rhode Island",
        "South Carolina",
        "South Dakota",
        "Tennessee",
        "Texas",
        "Utah",
        "Vermont",
        "Virginia",
        "Washington",
        "West Virginia",
        "Wisconsin",
        "Wyoming"
    )


    var category by remember {
        mutableStateOf("")
    }

    val heightTextFields by remember {
        mutableStateOf(55.dp)
    }

    var textFieldSize by remember {
        mutableStateOf(Size.Zero)
    }

    var expanded by remember {
        mutableStateOf(false)
    }
    val interactionSource = remember {
        MutableInteractionSource()
    }

    // Category Field
    Column(
        modifier = Modifier
//            .padding(30.dp)
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = {
                    expanded = false
                }
            )
    ) {

        Column(modifier = Modifier.fillMaxWidth()) {

            Row(modifier = Modifier.fillMaxWidth()) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(heightTextFields)
//                        .border(
//                            width = 1.8.dp,
//                            color = Color.Black,
//                            shape = RoundedCornerShape(15.dp)
//                        )
                        .onGloballyPositioned { coordinates ->
                            textFieldSize = coordinates.size.toSize()
                        },
                    value = category,
                    onValueChange = {
                        category = it
                        expanded = true
                    },
                    placeholder = { Text("Enter a location") },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent, // Optional: if you have a disabled state
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent, // Optional: if you have a disabled state
                        cursorColor = Color.Black
                    ),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp
                    ),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
//                    trailingIcon = {
//                        IconButton(onClick = { expanded = !expanded }) {
//                            Icon(
//                                modifier = Modifier.size(24.dp),
//                                imageVector = Icons.Rounded.KeyboardArrowDown,
//                                contentDescription = "arrow",
//                                tint = Color.Black
//                            )
//                        }
//                    }
                )
            }

            AnimatedVisibility(visible = expanded) {
                Card(
                    modifier = Modifier
                        .padding(horizontal = 5.dp)
                        .width(textFieldSize.width.dp),
                    shape = RoundedCornerShape(10.dp)
                ) {

                    LazyColumn(
                        modifier = Modifier.heightIn(max = 150.dp),
                    ) {

                        if (category.isNotEmpty()) {
                            items(
                                states.filter {
                                    it.lowercase()
                                        .contains(category.lowercase()) || it.lowercase()
                                        .contains("others")
                                }
                                    .sorted()
                            ) {
                                ItemsCategory(title = it) { title ->
                                    category = title
                                    expanded = false
                                }
                            }
                        } else {
                            items(
                                states.sorted()
                            ) {
                                ItemsCategory(title = it) { title ->
                                    category = title
                                    expanded = false
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}



@Composable
fun ItemsCategory(
    title: String,
    onSelect: (String) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onSelect(title)
            }
            .padding(10.dp)
    ) {
        Text(text = title, fontSize = 16.sp)
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuerySearch(
    modifier: Modifier = Modifier,
    query: String = "",
    screenType: String = "Enter a Location",
    colors: TextFieldColors? = null,
    onDoneActionClick: () -> Unit = {},
    onClearActionClick: () -> Unit = {},
    onQueryChanged: (String) -> Unit = {}
) {
    val focusRequester = FocusRequester()
    val focusManager = LocalFocusManager.current
    Row(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = modifier
                .weight(1f)
                .wrapContentHeight()
                .focusRequester(focusRequester),
            value = query,
            placeholder = {
                Text(
                    text = screenType,
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = SilverChalice
                    ),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            },
            onValueChange = {
                val input = it.filter { filter ->
                    !filter.isSurrogate()
                }
                onQueryChanged(input)
            },
            textStyle = MaterialTheme.typography.labelSmall.copy(
                lineHeight = 27.2.sp,
                letterSpacing = 0.45.sp,
                color = Black
            ),
            singleLine = true,
            keyboardActions = KeyboardActions(
                onSearch = {
                    focusManager.clearFocus()
                    onDoneActionClick()
                }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
                keyboardType = KeyboardType.Text
            ),
            colors = colors ?: outlinedTextFieldColors(
                focusedBorderColor = Color.Transparent,
                unfocusedBorderColor = Color.Transparent
            )
        )
        if (query.isNotEmpty()) {
            Icon(
                painter = painterResource(Res.drawable.ic_clear),
                contentDescription = "",
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(24.dp)
                    .clickable {
                        onClearActionClick()
                    }
            )
        }
    }

    DisposableEffect(Unit) {
        focusRequester.requestFocus()
        onDispose { }
    }
}