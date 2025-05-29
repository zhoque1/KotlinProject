package org.demo.project.features.posts.domain.useCase

import org.demo.project.features.posts.domain.IPostsRepository
import org.demo.project.features.posts.domain.model.Post
import org.demo.project.core.domain.Result
import org.demo.project.core.domain.DataError

interface IGetSomePostsUseCase {
    suspend operator fun invoke(): Result<List<Post>, DataError.Remote>
}

class GetSomePostsUseCase(
    private val postsRepository: IPostsRepository
): IGetSomePostsUseCase {

    override suspend fun invoke(): Result<List<Post>, DataError.Remote>{
        return postsRepository.getSomePosts()
    }
}