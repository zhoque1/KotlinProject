package org.demo.project.features.posts.domain.useCase

import org.demo.project.core.domain.DataError
import org.demo.project.features.posts.domain.model.Post
import org.demo.project.core.domain.Result

interface IGetSomePostsUseCase {
    suspend operator fun invoke(): Result<List<Post>, DataError.Remote>
}