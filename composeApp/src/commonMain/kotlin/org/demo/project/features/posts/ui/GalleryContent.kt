package org.demo.project.features.posts.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.demo.project.features.posts.domain.Post


@Composable
fun GalleryContent(modifier: Modifier = Modifier, list: List<Post>) {

    LazyColumn(modifier.fillMaxSize()) {
        items(list) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 4.dp)
                    .fillMaxWidth()
            ) {
                Text(text = it.id.toString())
                Spacer(Modifier.height(4.dp))
                Text(text = it.title, style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.height(4.dp))
                Text(text = it.body, style = MaterialTheme.typography.bodySmall)

            }
        }
    }

}