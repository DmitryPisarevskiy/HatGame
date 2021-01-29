package com.dmitry.pisarevskiy.hatgame.data.model

import java.util.*
import kotlin.collections.ArrayList

class Game(val id: String, var list: MutableList<Word>) {
    var currentWord: Word = list[(Math.random() * list.size).toInt()]
    var isOver: Boolean = false
    var playedWords: Int = 0
    var notPlayedWords: Int = list.size
    var guessedWords: Int = 0

    constructor(id: String, list: MutableList<Word>, numOfWords: Int) : this(id, list) {
        val listCopy: MutableList<Word> = LinkedList(list)
        list.clear()
        var index: Int
        for (i in 1..numOfWords) {
            if (listCopy.size == 0) break
            index = (Math.random() * listCopy.size).toInt()
            list.add(listCopy[index])
            listCopy.removeAt(index)
        }
        notPlayedWords = list.size
    }

    fun nextWord(currentWordIsGuessed: Boolean) {
        currentWord.isPlayed = true
        notPlayedWords--
        playedWords++
        if (currentWordIsGuessed) {
            currentWord.isGuessed = true
            guessedWords++
        }

        if (notPlayedWords == 0) {
            isOver = true
        } else {
            val listOfUnplayedWords: MutableList<Word> = ArrayList()
            for (i in list.indices) {
                if (!list[i].isPlayed) {
                    listOfUnplayedWords.add(list[i])
                }
            }
            currentWord = listOfUnplayedWords[(Math.random() * listOfUnplayedWords.size).toInt()]
        }
    }
}