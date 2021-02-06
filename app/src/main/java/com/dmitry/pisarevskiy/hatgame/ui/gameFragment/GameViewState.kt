package com.dmitry.pisarevskiy.hatgame.ui.gameFragment

import com.dmitry.pisarevskiy.hatgame.ui.base.BaseViewState

data class GameViewState(var currentWord: String, var gameIsOver: Boolean = false): BaseViewState