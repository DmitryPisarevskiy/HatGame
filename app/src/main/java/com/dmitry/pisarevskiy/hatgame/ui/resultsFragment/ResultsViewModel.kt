package com.dmitry.pisarevskiy.hatgame.ui.resultsFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ResultsViewModel() : ViewModel() {
    private val viewStateLiveData: MutableLiveData<ResultsViewState> = MutableLiveData()

    fun viewState(): LiveData<ResultsViewState> = viewStateLiveData

    fun changeResults(numOfWords: Int, numOfGuessedWords: Int) {
        viewStateLiveData.value = ResultsViewState(numOfWords, numOfGuessedWords)
    }
}