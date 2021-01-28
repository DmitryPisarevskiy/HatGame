package com.dmitry.pisarevskiy.hatgame.ui.gameFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmitry.pisarevskiy.hatgame.data.Repository
import com.dmitry.pisarevskiy.hatgame.data.model.Game
import com.dmitry.pisarevskiy.hatgame.data.model.GameTypes

const val NUM_OF_WORDS_IN_NEW_GAME = 7

class GameViewModel () : ViewModel() {
    private val viewStateLiveData: MutableLiveData<GameViewState> = MutableLiveData()
    private var gameType:GameTypes = GameTypes.SAVED

    init {
        Repository.currentGame = Repository.savedGame
        viewStateLiveData.value = GameViewState(Repository.currentGame.currentWord.name)
    }

    fun viewState(): LiveData<GameViewState> = viewStateLiveData

    fun nextWord(isGuessed:Boolean) {
        Repository.currentGame.currentWord.isPlayed = true
        Repository.currentGame.currentWord.isGuessed = isGuessed
        Repository.currentGame.nextWord()
        viewStateLiveData.value = GameViewState(Repository.currentGame.currentWord.name)
    }

    fun changeGameModeToNew() {
        gameType = GameTypes.NEW
        Repository.currentGame = Game("1", Repository.words, NUM_OF_WORDS_IN_NEW_GAME)
        viewStateLiveData.value = GameViewState(Repository.currentGame.currentWord.name)
    }
}