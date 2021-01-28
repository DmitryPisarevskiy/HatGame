package com.dmitry.pisarevskiy.hatgame.ui.mainFragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val viewStateLiveData: MutableLiveData<MainViewState> = MutableLiveData()

    fun viewState(): LiveData<MainViewState> = viewStateLiveData
}