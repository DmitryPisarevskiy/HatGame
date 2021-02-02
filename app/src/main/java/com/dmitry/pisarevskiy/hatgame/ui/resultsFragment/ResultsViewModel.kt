package com.dmitry.pisarevskiy.hatgame.ui.resultsFragment

import com.dmitry.pisarevskiy.hatgame.data.Repository
import com.dmitry.pisarevskiy.hatgame.ui.base.BaseViewModel

class ResultsViewModel(repository: Repository = Repository) : BaseViewModel<ResultsViewState>() {
    init {
        viewStateLiveData.value =
            ResultsViewState(repository.currentGame.numOfPlayedWords, repository.currentGame.guessedWords)
    }
}