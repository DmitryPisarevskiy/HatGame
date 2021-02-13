package com.dmitry.pisarevskiy.hatgame.data.model

data class Word(
    val name: String = "",
    val category: Category = Category.OTHERS,
    var guessed: Boolean = false,
    var played: Boolean = false,
)