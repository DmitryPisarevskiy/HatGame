package com.dmitry.pisarevskiy.hatgame.data

import com.dmitry.pisarevskiy.hatgame.data.model.Categories
import com.dmitry.pisarevskiy.hatgame.data.model.Game
import com.dmitry.pisarevskiy.hatgame.data.model.Word

object Repository {
    internal val game=Game("0", listOf(
        Word("Жульен", false, Categories.FOOD),
        Word("Сок", false, Categories.FOOD),
        Word("Ракета", false, Categories.SCIENCE),
        Word("Пифагор", false, Categories.SCIENCE),
        Word("Косинус", false, Categories.SCIENCE),
        Word("Камасутра", false, Categories.MORE_THEN_81),
        Word("Контрацептив", false, Categories.MORE_THEN_81),
    ))
}