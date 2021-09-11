package com.jmb.prueba

import android.app.Application
import com.jmb.data.repository.UsersRepository
import com.jmb.data.source.LocalDataSource
import com.jmb.data.source.RemoteDataSource
import com.jmb.prueba.model.server.TheUserDbDataSource
import com.jmb.prueba.model.server.UserDB
import com.jmb.prueba.ui.main.MainActivity
import com.jmb.prueba.ui.main.MainViewModel
import com.jmb.prueba.ui.posts.PostActivity
import com.jmb.usecases.GetUsers
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module


fun Application.initDI() {
    startKoin {
        androidLogger()
        androidContext(this@initDI)
        modules(listOf(appModule, dataModule, scopesModule))
    }
}

private val appModule = module {
    factory<RemoteDataSource> { TheUserDbDataSource(get()) }
    single<CoroutineDispatcher> { Dispatchers.Main }
    single(named("baseUrl")) { "https://jsonplaceholder.typicode.com/" }
    single { UserDB(get(named("baseUrl"))) }
}

val dataModule = module {
    factory { UsersRepository(get()) }
}

private val scopesModule = module {
    scope(named<MainActivity>()) {
        viewModel { MainViewModel(get()) }
        scoped { GetUsers(get()) }
    }

    /*scope(named<PostActivity>()) {
        viewModel { (id: Int) -> DetailViewModel(id, get(), get(), get()) }
        scoped { FindMovieById(get()) }
        scoped { ToggleMovieFavorite(get()) }
    }*/
}