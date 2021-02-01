package com.dmitry.pisarevskiy.hatgame.data.provider

import androidx.lifecycle.LiveData
import com.dmitry.pisarevskiy.hatgame.data.WordResult
import com.dmitry.pisarevskiy.hatgame.data.model.Word

interface RemoteDataProvider {
    fun subscribeToAllWords(): LiveData<WordResult>
    fun getWordByName(name:String) : LiveData<WordResult>
    fun saveWord(word: Word):LiveData<WordResult>
}