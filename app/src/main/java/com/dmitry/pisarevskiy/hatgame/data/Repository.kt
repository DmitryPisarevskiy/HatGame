package com.dmitry.pisarevskiy.hatgame.data

import com.dmitry.pisarevskiy.hatgame.data.model.Categories
import com.dmitry.pisarevskiy.hatgame.data.model.Game
import com.dmitry.pisarevskiy.hatgame.data.model.Word

object Repository {
    lateinit var currentGame: Game

    var savedGame = Game(
        "0", mutableListOf(
            Word("Жульен", Categories.FOOD),
            Word("Сок", Categories.FOOD),
            Word("Ракета", Categories.SCIENCE),
            Word("Пифагор", Categories.SCIENCE),
            Word("Косинус", Categories.SCIENCE),
            Word("Камасутра", Categories.MORE_THEN_18),
            Word("Контрацептив", Categories.MORE_THEN_18),
        )
    )

    val words = mutableListOf(
        Word("Тангенс", Categories.SCIENCE),
        Word("Гипотеза", Categories.SCIENCE),
        Word("Эволюция", Categories.SCIENCE),
        Word("Беконечный ряд", Categories.SCIENCE),
        Word("Ромб", Categories.SCIENCE),
        Word("Жульен", Categories.FOOD),
        Word("Горох", Categories.FOOD),
        Word("Желатин", Categories.FOOD),
        Word("Медовуха", Categories.FOOD),
        Word("Яичница", Categories.FOOD),
        Word("Натюрморт", Categories.ARTS),
        Word("Мазок", Categories.ARTS),
        Word("Талант", Categories.ARTS),
        Word("Припев", Categories.ARTS),
        Word("Мольберт", Categories.ARTS),
        Word("Колесо", Categories.CARS),
        Word("Мазда", Categories.CARS),
        Word("Лошадиная сила", Categories.CARS),
        Word("Запасное колесо", Categories.CARS),
        Word("Жигули", Categories.CARS),
        Word("Иван Грозный", Categories.HISTORY),
        Word("Ледовое побоище", Categories.HISTORY),
        Word("Кириллица", Categories.HISTORY),
        Word("Мушкетер", Categories.HISTORY),
        Word("Восстание", Categories.HISTORY),
        Word("Камасутра", Categories.MORE_THEN_18),
        Word("Контрацептив", Categories.MORE_THEN_18),
        Word("Поза 69", Categories.MORE_THEN_18),
        Word("Эротика", Categories.MORE_THEN_18),
        Word("Кунилингус", Categories.MORE_THEN_18),
        Word("Эйс", Categories.SPORT),
        Word("Тайм-аут", Categories.SPORT),
        Word("Гол", Categories.SPORT),
        Word("Булит", Categories.SPORT),
        Word("Рекорд", Categories.SPORT),
    )

}