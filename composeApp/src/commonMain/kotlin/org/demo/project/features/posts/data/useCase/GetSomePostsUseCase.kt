package org.demo.project.features.posts.data.useCase

import org.demo.project.features.posts.domain.IPostsRepository
import org.demo.project.features.posts.domain.model.Post
import org.demo.project.features.posts.domain.useCase.IGetSomePostsUseCase
import org.demo.project.core.domain.Result
import org.demo.project.core.domain.DataError


class GetSomePostsUseCase(
    private val postsRepository: IPostsRepository
): IGetSomePostsUseCase {

    override suspend fun invoke(): Result<List<Post>, DataError.Remote>{
        return postsRepository.getSomePosts()
    }
}