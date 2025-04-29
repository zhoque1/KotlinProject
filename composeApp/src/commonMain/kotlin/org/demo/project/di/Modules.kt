package org.demo.project.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.demo.project.features.books.data.database.DatabaseFactory
import org.demo.project.features.books.data.database.FavoriteBookDatabase
import org.demo.project.features.books.data.network.KtorRemoteBookDataSource
import org.demo.project.features.books.data.network.RemoteBookDataSource
import org.demo.project.features.books.data.repository.DefaultBookRepository
import org.demo.project.features.books.domain.BookRepository
import org.demo.project.features.books.presentation.SelectedBookViewModel
import org.demo.project.features.books.presentation.book_detail.BookDetailViewModel
import org.demo.project.features.books.presentation.book_list.BookListViewModel
import org.demo.project.core.data.HttpClientFactory
import org.demo.project.features.posts.data.PostsRepositoryImp
import org.demo.project.features.posts.data.network.RemoteDataSource
import org.demo.project.features.posts.data.network.RemoteDataSourceImp
import org.demo.project.features.posts.domain.PostsRepository
import org.demo.project.features.posts.ui.PostsViewModel
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

    singleOf(::RemoteDataSourceImp).bind<RemoteDataSource>()
    singleOf(::PostsRepositoryImp).bind<PostsRepository>()

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<FavoriteBookDatabase>().favoriteBookDao }

    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailViewModel)
    viewModelOf(::SelectedBookViewModel)

    viewModelOf(::PostsViewModel)
}