package com.dmitry.pisarevskiy.hatgame.data.model

class Game(val id:String, var list: List<Word>) {
    var currentWord: Word = getNextWord()

    fun getNextWord(): Word {
        val listOfUnplayedWords: MutableList<Word>  = ArrayList()
        for (i in list.indices) {
            if (!list[i].isPlayed) {
                listOfUnplayedWords.add(list[i])
            }
        }
        return if (list.isEmpty()) {
            Word("",false,Categories.MORE_THEN_81)
        } else {
            listOfUnplayedWords[(Math.random()*listOfUnplayedWords.size).toInt()]
        }
    }
}