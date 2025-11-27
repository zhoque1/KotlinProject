package org.demo.project.features.posts.domain.useCase

import org.demo.project.features.posts.domain.IPostsRepository
import org.demo.project.features.posts.domain.model.Post

interface IGetPostsUseCase {
    suspend operator fun invoke(): List<Post>
}

class GetPostsUseCase(
    private val postsRepository: IPostsRepository
): IGetPostsUseCase {

    override suspend fun invoke(): List<Post>{
        return emptyList() // postsRepository.getPosts()
    }
}