package com.dmitry.pisarevskiy.hatgame.data.model

import java.util.*
import kotlin.collections.ArrayList

class Game(val id: String, var list: MutableList<Word>) {
    var currentWord: Word = list[(Math.random() * list.size).toInt()]

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
    }

    fun nextWord() {
        val listOfUnplayedWords: MutableList<Word> = ArrayList()
        for (i in list.indices) {
            if (!list[i].isPlayed) {
                listOfUnplayedWords.add(list[i])
            }
        }
        return if (list.isEmpty()) {
            currentWord = Word("", false, Categories.MORE_THEN_18)
        } else {
            currentWord = listOfUnplayedWords[(Math.random() * listOfUnplayedWords.size).toInt()]
        }
    }
}