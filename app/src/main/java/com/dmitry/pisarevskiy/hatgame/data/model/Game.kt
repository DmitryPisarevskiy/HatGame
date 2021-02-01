package com.dmitry.pisarevskiy.hatgame.data.model

class Game(val id: String, var list: MutableList<Word>, val numOfWords: Int = list.size) {
    val iterator: Iterator<Word> by lazy { list.iterator() }
    var currentWord: Word
    var isOver: Boolean = false
    var numOfPlayedWords: Int = 0
    var guessedWords: Int = 0

    init {
        list.shuffle()
        currentWord = iterator.next()
    }

    fun nextWord(currentWordIsGuessed: Boolean) {
        currentWord.isPlayed = true
        numOfPlayedWords++
        if (currentWordIsGuessed) {
            currentWord.isGuessed = true
            guessedWords++
        }

        if (numOfPlayedWords != numOfWords) {
            currentWord = iterator.next()
        } else {
            isOver = true
        }
    }
}