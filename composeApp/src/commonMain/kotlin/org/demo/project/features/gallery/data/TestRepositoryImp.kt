package org.demo.project.features.gallery.data

import org.demo.project.core.domain.DataError
import org.demo.project.features.posts.data.network.RemoteDataSource
import org.demo.project.features.posts.domain.Post
import org.demo.project.core.domain.Result
import org.demo.project.features.gallery.data.network.TestDataSource
import org.demo.project.features.gallery.domain.TestRepository

class TestRepositoryImp(
    private val testDataSource: TestDataSource
): TestRepository {
    override suspend fun getSomePosts(): Result<List<Post>, DataError.Remote>{
        val response = testDataSource.getSomePosts() //.map { item -> item.map { it.toPost() } }
        return response
    }
}