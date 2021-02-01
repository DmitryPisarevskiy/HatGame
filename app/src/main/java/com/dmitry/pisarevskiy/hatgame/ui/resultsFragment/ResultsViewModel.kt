package com.dmitry.pisarevskiy.hatgame.ui.resultsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmitry.pisarevskiy.hatgame.data.Repository

class ResultsViewModel : ViewModel() {
    private val viewStateLiveData: MutableLiveData<ResultsViewState> = MutableLiveData()

    fun viewState(): LiveData<ResultsViewState> = viewStateLiveData

    fun getResults() {
        Repository.apply {
            viewStateLiveData.value = ResultsViewState(currentGame.numOfPlayedWords, currentGame.guessedWords)
        }
    }

}