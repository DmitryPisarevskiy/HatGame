package com.dmitry.pisarevskiy.hatgame.ui.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class BaseViewModel<S>: ViewModel() {
    open val viewStateLiveData = MutableLiveData<S>()

    open fun viewState(): LiveData<S> = viewStateLiveData

}