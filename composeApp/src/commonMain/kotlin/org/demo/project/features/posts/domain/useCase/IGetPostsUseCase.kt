package org.demo.project.features.posts.domain.useCase

import org.demo.project.features.posts.domain.model.Post


interface IGetPostsUseCase {
    suspend operator fun invoke(): List<Post>
}