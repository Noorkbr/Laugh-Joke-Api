package com.example.laughjoke.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.laughjoke.service.JokesClient
import com.example.laughjoke.service.ResponseJokes
import kotlinx.coroutines.launch


class JokesViewModel(application: Application) : AndroidViewModel(application) {
    private val responseJokesData = MutableLiveData<ResponseJokes>()
    val _responseJokesData get() = responseJokesData

    private val emptyValue = ""
    val jokeSetup = MutableLiveData<String>()
    val delivery = MutableLiveData<String>()
    val categories = MutableLiveData<String>()
    private val repository: DefaultJokesRepository = DefaultJokesRepository.getInstance(application)
    private lateinit var jokesData: JokesData

    fun getRandomJokes(category: String) {
        viewModelScope.launch {
            val response = when (category) {
                "Any" -> JokesClient.jokeApiService.getRandomJoke()
                "Programming" -> JokesClient.jokeApiService.getProgrammingJoke()
                "Miscellaneous" -> JokesClient.jokeApiService.getMiscellaneousJoke()
                "Dark" -> JokesClient.jokeApiService.getDarkJoke()
                "Pun" -> JokesClient.jokeApiService.getPunJoke()
                "Spooky" -> JokesClient.jokeApiService.getSpookyJoke()
                "Christmas" -> JokesClient.jokeApiService.getChristmasJoke()
                else -> JokesClient.jokeApiService.getRandomJoke()
            }
            if (response.isSuccessful) {
                responseJokesData.postValue(response.body())
            }
        }

    }

    fun saveJokes() {
        val jokeSetup = jokeSetup.value
        val delivery = delivery.value

        if (jokeSetup == emptyValue){
            jokesData = JokesData(
                joke = delivery,
                type = categories.value.toString()
            )
        }else{
            jokesData = JokesData(
                setup = jokeSetup,
                delivery = delivery,
                type = categories.value.toString()
            )
        }

        saveJokes(jokesData)

    }

    private fun saveJokes(jokesData: JokesData){
       /* viewModelScope.launch{
            repository.saveJokes(jokesData)
        }*/
    }
}
