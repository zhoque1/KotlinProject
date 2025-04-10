package org.demo.project.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import org.demo.project.features.data.database.DatabaseFactory
import org.demo.project.features.data.database.FavoriteBookDatabase
import org.demo.project.features.data.network.KtorRemoteBookDataSource
import org.demo.project.features.data.network.RemoteBookDataSource
import org.demo.project.features.data.repository.DefaultBookRepository
import org.demo.project.features.domain.BookRepository
import org.demo.project.features.presentation.SelectedBookViewModel
import org.demo.project.features.presentation.book_detail.BookDetailViewModel
import org.demo.project.features.presentation.book_list.BookListViewModel
import org.demo.project.core.data.HttpClientFactory
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

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<FavoriteBookDatabase>().favoriteBookDao }

    viewModelOf(::BookListViewModel)
    viewModelOf(::BookDetailViewModel)
    viewModelOf(::SelectedBookViewModel)
}