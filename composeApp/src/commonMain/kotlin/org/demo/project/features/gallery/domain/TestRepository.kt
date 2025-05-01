package org.demo.project.features.gallery.domain

import org.demo.project.core.domain.DataError
import org.demo.project.core.domain.Result
import org.demo.project.features.posts.domain.Post


interface TestRepository {
    suspend fun getSomePosts(): Result<List<Post>, DataError.Remote>
}