package com.dmitry.pisarevskiy.hatgame.data.model

data class Word(val name:String, var isGuessed:Boolean, val category:Categories) {
    var isPlayed: Boolean = false
}