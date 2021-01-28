package com.dmitry.pisarevskiy.hatgame.data

import com.dmitry.pisarevskiy.hatgame.data.model.Categories
import com.dmitry.pisarevskiy.hatgame.data.model.Game
import com.dmitry.pisarevskiy.hatgame.data.model.Word

object Repository {
    lateinit var currentGame: Game

    var savedGame = Game(
        "0", mutableListOf(
            Word("Жульен", false, Categories.FOOD),
            Word("Сок", false, Categories.FOOD),
            Word("Ракета", false, Categories.SCIENCE),
            Word("Пифагор", false, Categories.SCIENCE),
            Word("Косинус", false, Categories.SCIENCE),
            Word("Камасутра", false, Categories.MORE_THEN_18),
            Word("Контрацептив", false, Categories.MORE_THEN_18),
        )
    )

    val words = mutableListOf(
        Word("Тангенс", false, Categories.SCIENCE),
        Word("Гипотеза", false, Categories.SCIENCE),
        Word("Эволюция", false, Categories.SCIENCE),
        Word("Беконечный ряд", false, Categories.SCIENCE),
        Word("Ромб", false, Categories.SCIENCE),
        Word("Жульен", false, Categories.FOOD),
        Word("Горох", false, Categories.FOOD),
        Word("Желатин", false, Categories.FOOD),
        Word("Медовуха", false, Categories.FOOD),
        Word("Яичница", false, Categories.FOOD),
        Word("Натюрморт", false, Categories.ARTS),
        Word("Мазок", false, Categories.ARTS),
        Word("Талант", false, Categories.ARTS),
        Word("Припев", false, Categories.ARTS),
        Word("Мольберт", false, Categories.ARTS),
        Word("Колесо", false, Categories.CARS),
        Word("Мазда", false, Categories.CARS),
        Word("Лошадиная сила", false, Categories.CARS),
        Word("Запасное колесо", false, Categories.CARS),
        Word("Жигули", false, Categories.CARS),
        Word("Иван Грозный", false, Categories.HISTORY),
        Word("Ледовое побоище", false, Categories.HISTORY),
        Word("Кириллица", false, Categories.HISTORY),
        Word("Мушкетер", false, Categories.HISTORY),
        Word("Восстание", false, Categories.HISTORY),
        Word("Камасутра", false, Categories.MORE_THEN_18),
        Word("Контрацептив", false, Categories.MORE_THEN_18),
        Word("Поза 69", false, Categories.MORE_THEN_18),
        Word("Эротика", false, Categories.MORE_THEN_18),
        Word("Кунилингус", false, Categories.MORE_THEN_18),
        Word("Эйс", false, Categories.SPORT),
        Word("Тайм-аут", false, Categories.SPORT),
        Word("Гол", false, Categories.SPORT),
        Word("Булит", false, Categories.SPORT),
        Word("Рекорд", false, Categories.SPORT),
    )

}