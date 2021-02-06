package com.dmitry.pisarevskiy.hatgame

import com.dmitry.pisarevskiy.hatgame.data.Repository
import com.dmitry.pisarevskiy.hatgame.data.model.Game
import com.dmitry.pisarevskiy.hatgame.ui.gameFragment.NUM_OF_WORDS_IN_NEW_GAME
import org.junit.Test

import org.junit.Assert.*

class GameTest {

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun unplayedWordChoice() {
        val game = Repository.savedGame
        while (!game.isOver) {
            println(game.currentWord.name)
            game.nextWord(true)
        }
        assertEquals(1,1)
    }

    @Test
    fun newGameCreation() {
        val game = Game("1",Repository.words, NUM_OF_WORDS_IN_NEW_GAME)
        while (!game.isOver) {
            println(game.currentWord.name)
            game.nextWord(true)
        }
        assertEquals(1,1)
    }
}