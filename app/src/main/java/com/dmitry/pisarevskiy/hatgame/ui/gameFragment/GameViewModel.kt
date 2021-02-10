package com.dmitry.pisarevskiy.hatgame.ui.gameFragment

import com.dmitry.pisarevskiy.hatgame.data.NEW_GAME_ID
import com.dmitry.pisarevskiy.hatgame.data.Repository
import com.dmitry.pisarevskiy.hatgame.data.model.GameType
import com.dmitry.pisarevskiy.hatgame.ui.base.BaseViewModel

const val NUM_OF_WORDS_IN_NEW_GAME = 7

class GameViewModel(val repository: Repository = Repository) : BaseViewModel<GameViewState>() {
    private var gameType: GameType = GameType.NEW

    init {
        viewStateLiveData.value = GameViewState(gameIsReady = false)
    }

    fun nextWord(isGuessed: Boolean) {
        repository.currentGame?.let {currentGame ->
            currentGame.nextWord(isGuessed)
            if (!currentGame.isOver) {
                viewStateLiveData.value = GameViewState(
                    currentWord = currentGame.currentWord.name,
                    gameIsReady = true
                )
            } else {
                viewStateLiveData.value = GameViewState(
                    currentGame.currentWord.name,
                    currentGame.isOver
                )
            }
        }
        if (repository.gameIsReady) {
            repository.currentGame!!.nextWord(isGuessed)
            if (!repository.currentGame!!.isOver) {
                viewStateLiveData.value = GameViewState(
                    currentWord = repository.currentGame!!.currentWord.name,
                    gameIsReady = true
                )
            } else {
                viewStateLiveData.value = GameViewState(
                    repository.currentGame!!.currentWord.name,
                    repository.currentGame!!.isOver
                )
            }
        }
    }

    fun getGame(gameID: String = NEW_GAME_ID) {
        repository.getGame(gameID)
        repository.gameLiveData.observeForever { game ->
            viewStateLiveData.value = GameViewState(
                currentWord = repository.currentGame!!.currentWord.name,
                gameIsReady = true
            )
        }
    }
}