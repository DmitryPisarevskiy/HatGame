package com.dmitry.pisarevskiy.hatgame.ui.newGameFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dmitry.pisarevskiy.hatgame.data.Repository

class NewGameViewModel: ViewModel() {
    private val viewStateLiveData: MutableLiveData<NewGameViewState> = MutableLiveData()

    init {
        viewStateLiveData.value = NewGameViewState(Repository.game)
    }

    fun viewState(): LiveData<NewGameViewState>  = viewStateLiveData
}