package com.dmitry.pisarevskiy.hatgame.data

sealed class WordResult {
    data class Success<out T>(val data: T) : WordResult()
    data class Error(val error: Throwable) : WordResult()
}
