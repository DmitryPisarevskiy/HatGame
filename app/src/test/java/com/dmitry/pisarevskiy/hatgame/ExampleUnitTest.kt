package com.dmitry.pisarevskiy.hatgame

import com.dmitry.pisarevskiy.hatgame.data.model.Categories
import com.dmitry.pisarevskiy.hatgame.data.model.Game
import com.dmitry.pisarevskiy.hatgame.data.model.Word
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun unplayedWordChoice() {
        val game= Game("0", listOf(
            Word("Жульен", false, Categories.FOOD),
            Word("Сок", false, Categories.FOOD),
            Word("Ракета", false, Categories.SCIENCE),
            Word("Пифагор", false, Categories.SCIENCE),
            Word("Косинус", false, Categories.SCIENCE),
            Word("Камасутра", false, Categories.MORE_THEN_81),
            Word("Контрацептив", false, Categories.MORE_THEN_81),
        ))
        lateinit var word: Word
        for (i in game.list) {
            word= game.getNextWord()
            word.isPlayed = true
            println(word.name)
        }
        assertEquals(1,1)
    }
}