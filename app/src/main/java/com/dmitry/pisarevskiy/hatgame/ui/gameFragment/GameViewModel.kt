package com.dmitry.pisarevskiy.hatgame.ui.gameFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.dmitry.pisarevskiy.hatgame.data.Repository
import com.dmitry.pisarevskiy.hatgame.data.WordResult
import com.dmitry.pisarevskiy.hatgame.data.WordResult.Success
import com.dmitry.pisarevskiy.hatgame.data.model.Game
import com.dmitry.pisarevskiy.hatgame.data.model.GameType
import com.dmitry.pisarevskiy.hatgame.ui.base.BaseViewModel
import com.dmitry.pisarevskiy.hatgame.ui.mainFragment.MainViewState

const val NUM_OF_WORDS_IN_NEW_GAME = 7

class GameViewModel(val repository: Repository = Repository) : BaseViewModel<GameViewState>() {
//    private val viewStateLiveData: MutableLiveData<GameViewState> = MutableLiveData()
    private var gameType: GameType = GameType.SAVED

    init {
        repository.currentGame = repository.savedGame
        viewStateLiveData.value = GameViewState(repository.currentGame.currentWord.name)
    }

//    fun viewState(): LiveData<GameViewState> = viewStateLiveData

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
        repository.currentGame = Game("1", repository.words, NUM_OF_WORDS_IN_NEW_GAME)
        viewStateLiveData.value = GameViewState(repository.currentGame.currentWord.name)
    }
}