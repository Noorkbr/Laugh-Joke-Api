package com.example.jokerjokes.db

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.jokerjokes.db.local.JokesDatabase
import com.example.jokerjokes.db.local.LocalDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class DefaultJokesRepository private constructor(application: Application) {
    private val localDataSource: LocalDataSource
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

    init {
        localDataSource =
            LocalDataSource(JokesDatabase.getInstance(application.applicationContext).jokeDao())
    }

    companion object{
        private var repository: DefaultJokesRepository? = null
        fun getInstance(application: Application): DefaultJokesRepository{
            if (repository == null){
                repository = DefaultJokesRepository(application)
                return repository as DefaultJokesRepository
            }
            return repository as DefaultJokesRepository
        }
    }

    suspend fun saveJokes(jokesData: JokesData){
        withContext(ioDispatcher){
            localDataSource.saveJokes(jokesData)
        }
    }

    fun getAllJokes(): LiveData<List<JokesData>> {
        return localDataSource.getAllJokes()
    }

    suspend fun deleteJokes(jokesData: JokesData){
        withContext(ioDispatcher){
            localDataSource.deleteJokes(jokesData)
        }
    }

}