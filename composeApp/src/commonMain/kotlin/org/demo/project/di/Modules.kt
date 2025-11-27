package org.demo.project.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.demo.project.core.data.HttpClientFactory
import org.demo.project.features.books.data.database.DatabaseFactory
import org.demo.project.features.books.data.database.FavoriteBookDatabase
import org.demo.project.features.books.data.network.KtorRemoteBookDataSource
import org.demo.project.features.books.data.network.RemoteBookDataSource
import org.demo.project.features.books.data.repository.DefaultBookRepository
import org.demo.project.features.books.domain.BookRepository
import org.demo.project.features.books.presentation.SelectedBookViewModel
import org.demo.project.features.books.presentation.book_detail.BookDetailViewModel
import org.demo.project.features.books.presentation.book_list.BookListViewModel
import org.demo.project.features.gallery.data.GalleryRepositoryImp
import org.demo.project.features.gallery.data.network.GalleryDataSource
import org.demo.project.features.gallery.data.network.GalleryDataSourceImp
import org.demo.project.features.gallery.domain.GalleryRepository
import org.demo.project.features.gallery.ui.GalleryViewModel
import org.demo.project.features.posts.data.PostsRepository
import org.demo.project.features.posts.domain.IPostDataStore
import org.demo.project.features.posts.domain.IPostsRepository
import org.demo.project.features.posts.domain.PostDataStore
import org.demo.project.features.posts.domain.useCase.GetPostsUseCase
import org.demo.project.features.posts.domain.useCase.GetSomePostsUseCase
import org.demo.project.features.posts.domain.useCase.IGetPostsUseCase
import org.demo.project.features.posts.domain.useCase.IGetSomePostsUseCase
import org.demo.project.features.posts.presentation.viewModel.PostDetailViewModel
import org.demo.project.features.posts.presentation.viewModel.PostsViewModel
import org.demo.project.features.product.data.ProductRepositoryImp
import org.demo.project.features.product.data.network.ProductDataSource
import org.demo.project.features.product.data.network.ProductDataSourceImp
import org.demo.project.features.product.domain.ProductRepository
import org.demo.project.features.product.ui.ProductViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::KtorRemoteBookDataSource).bind<RemoteBookDataSource>()
    singleOf(::DefaultBookRepository).bind<BookRepository>()

    singleOf(::PostsRepository).bind<IPostsRepository>()
    singleOf(::GetPostsUseCase).bind<IGetPostsUseCase>()
    singleOf(::GetSomePostsUseCase).bind<IGetSomePostsUseCase>()

//    singleOf(::TestDataSourceImp).bind<TestDataSource>()
//    singleOf(::TestRepositoryImp).bind<TestRepository>()

    singleOf(::GalleryDataSourceImp).bind<GalleryDataSource>()
    singleOf(::GalleryRepositoryImp).bind<GalleryRepository>()

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<FavoriteBookDatabase>().favoriteBookDao }

    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailViewModel)
    viewModelOf(::SelectedBookViewModel)

    single<IPostDataStore> { PostDataStore() }
    viewModelOf(::PostDetailViewModel)
    viewModelOf(::PostsViewModel)
//    viewModelOf(::TestViewModel)
    viewModelOf(::GalleryViewModel)


    single {
        HttpClient {
            install(ContentNegotiation) {
                json(json = Json { ignoreUnknownKeys = true }, contentType = ContentType.Any)
            }
        }
    }
    singleOf(::ProductRepositoryImp).bind<ProductRepository>()
    viewModelOf(::ProductViewModel)
}