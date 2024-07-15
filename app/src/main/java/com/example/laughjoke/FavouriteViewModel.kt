package com.example.laughjoke

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.laughjoke.db.DefaultJokesRepository
import com.example.laughjoke.db.JokesData
import kotlinx.coroutines.launch

class FavouriteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: DefaultJokesRepository = DefaultJokesRepository.getInstance(application)
    private lateinit var navController: NavController

    var getAllJokes: LiveData<List<JokesData>> = repository.getAllJokes()

    fun deleteJokes(jokesData: JokesData){
        viewModelScope.launch{
            repository.deleteJokes(jokesData)
        }

        }
    }


