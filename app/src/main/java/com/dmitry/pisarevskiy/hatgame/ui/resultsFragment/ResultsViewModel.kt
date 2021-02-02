package com.dmitry.pisarevskiy.hatgame.ui.resultsFragment

import com.dmitry.pisarevskiy.hatgame.data.Repository
import com.dmitry.pisarevskiy.hatgame.ui.base.BaseViewModel

class ResultsViewModel : BaseViewModel<ResultsViewState>() {
    fun getResults() {
        Repository.apply {
            viewStateLiveData.value = ResultsViewState(currentGame.numOfPlayedWords, currentGame.guessedWords)
        }
    }
}