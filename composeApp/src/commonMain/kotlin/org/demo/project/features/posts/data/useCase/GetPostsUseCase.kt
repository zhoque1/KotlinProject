package org.demo.project.features.posts.data.useCase

import org.demo.project.features.posts.domain.IPostsRepository
import org.demo.project.features.posts.domain.model.Post
import org.demo.project.features.posts.domain.useCase.IGetPostsUseCase


class GetPostsUseCase(
    private val postsRepository: IPostsRepository
): IGetPostsUseCase {

    override suspend fun invoke(): List<Post>{
        return postsRepository.getPosts()
    }
}