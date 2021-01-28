package com.dmitry.pisarevskiy.hatgame

import com.dmitry.pisarevskiy.hatgame.data.Repository
import com.dmitry.pisarevskiy.hatgame.data.model.Game
import com.dmitry.pisarevskiy.hatgame.data.model.Word
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GameTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun unplayedWordChoice() {
        var game = Repository.savedGame
        lateinit var word: Word
        for (i in game.list-1) {
            game.nextWord()
            word = game.currentWord
            word = game.currentWord
            word.isPlayed = true
            println(word.name)
        }
        assertEquals(1,1)
    }

    @Test
    fun newGameCreation() {
        Repository.currentGame = Game("1",Repository.words, 7)
        var game = Repository.currentGame
        lateinit var word: Word
        for (i in game.list-1) {
            game.nextWord()
            word = game.currentWord
            word = game.currentWord
            word.isPlayed = true
            println(word.name)
        }
        assertEquals(1,1)
    }
}