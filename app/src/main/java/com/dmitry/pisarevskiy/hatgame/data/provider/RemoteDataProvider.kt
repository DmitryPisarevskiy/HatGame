package com.dmitry.pisarevskiy.hatgame.data.provider

import androidx.lifecycle.LiveData
import com.dmitry.pisarevskiy.hatgame.data.WordResult
import com.dmitry.pisarevskiy.hatgame.data.model.User
import com.dmitry.pisarevskiy.hatgame.data.model.Word

interface RemoteDataProvider {
    fun subscribeToAllWords(): LiveData<WordResult>
    fun subscribeToSavedGame(gameID: String): LiveData<WordResult>
    fun getWordByName(gameID: String, name:String) : LiveData<WordResult>
    fun saveWord(gameID: String, word: Word):LiveData<WordResult>
    fun getCurrentUser(): LiveData<User?>
}