package com.dmitry.pisarevskiy.hatgame.ui.gameFragment

import com.dmitry.pisarevskiy.hatgame.data.Repository
import com.dmitry.pisarevskiy.hatgame.data.model.GameType
import com.dmitry.pisarevskiy.hatgame.ui.base.BaseViewModel

const val NUM_OF_WORDS_IN_NEW_GAME = 7

class GameViewModel(val repository: Repository = Repository) : BaseViewModel<GameViewState>() {
    private var gameType: GameType = GameType.SAVED

    init {
        repository.currentGame = repository.savedGame
        viewStateLiveData.value = GameViewState(repository.currentGame.currentWord.name)
    }

    fun nextWord(isGuessed: Boolean) {
        repository.currentGame.nextWord(isGuessed)
        if (!repository.currentGame.isOver) {
            viewStateLiveData.value = GameViewState(repository.currentGame.currentWord.name)
        } else {
            viewStateLiveData.value = GameViewState(
                repository.currentGame.currentWord.name,
                repository.currentGame.isOver
            )
        }
    }

    fun changeGameModeToNew() {
        gameType = GameType.NEW
        repository.currentGame = repository.newGame
        viewStateLiveData.value = GameViewState(repository.currentGame.currentWord.name)
    }
}