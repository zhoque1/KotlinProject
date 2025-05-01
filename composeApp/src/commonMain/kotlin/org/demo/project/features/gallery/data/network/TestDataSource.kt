package org.demo.project.features.gallery.data.network

import org.demo.project.core.domain.DataError
import org.demo.project.features.posts.domain.Post
import org.demo.project.core.domain.Result

interface TestDataSource{
    suspend fun getSomePosts():Result<List<Post>, DataError.Remote>
}