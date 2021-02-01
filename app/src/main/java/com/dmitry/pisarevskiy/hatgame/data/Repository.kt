package com.dmitry.pisarevskiy.hatgame.data

import androidx.annotation.VisibleForTesting
import com.dmitry.pisarevskiy.hatgame.data.model.Category
import com.dmitry.pisarevskiy.hatgame.data.model.Game
import com.dmitry.pisarevskiy.hatgame.data.model.Word
import com.dmitry.pisarevskiy.hatgame.data.provider.FireStoreProvider
import com.dmitry.pisarevskiy.hatgame.data.provider.RemoteDataProvider

object Repository {
    lateinit var currentGame: Game
    private val remoteProvider: RemoteDataProvider = FireStoreProvider()

    var savedGame = Game(
        "0", mutableListOf(
            Word("Жульен", Category.FOOD),
            Word("Сок", Category.FOOD),
            Word("Ракета", Category.SCIENCE),
            Word("Пифагор", Category.SCIENCE),
            Word("Косинус", Category.SCIENCE),
            Word("Камасутра", Category.MORE_THEN_18),
            Word("Контрацептив", Category.MORE_THEN_18),
        )
    )

    val words = mutableListOf(
        Word("Тангенс", Category.SCIENCE),
        Word("Гипотеза", Category.SCIENCE),
        Word("Эволюция", Category.SCIENCE),
        Word("Беконечный ряд", Category.SCIENCE),
        Word("Ромб", Category.SCIENCE),
        Word("Жульен", Category.FOOD),
        Word("Горох", Category.FOOD),
        Word("Желатин", Category.FOOD),
        Word("Медовуха", Category.FOOD),
        Word("Яичница", Category.FOOD),
        Word("Натюрморт", Category.ARTS),
        Word("Мазок", Category.ARTS),
        Word("Талант", Category.ARTS),
        Word("Припев", Category.ARTS),
        Word("Мольберт", Category.ARTS),
        Word("Колесо", Category.CARS),
        Word("Мазда", Category.CARS),
        Word("Лошадиная сила", Category.CARS),
        Word("Запасное колесо", Category.CARS),
        Word("Жигули", Category.CARS),
        Word("Иван Грозный", Category.HISTORY),
        Word("Ледовое побоище", Category.HISTORY),
        Word("Кириллица", Category.HISTORY),
        Word("Мушкетер", Category.HISTORY),
        Word("Восстание", Category.HISTORY),
        Word("Камасутра", Category.MORE_THEN_18),
        Word("Контрацептив", Category.MORE_THEN_18),
        Word("Поза 69", Category.MORE_THEN_18),
        Word("Эротика", Category.MORE_THEN_18),
        Word("Кунилингус", Category.MORE_THEN_18),
        Word("Эйс", Category.SPORT),
        Word("Тайм-аут", Category.SPORT),
        Word("Гол", Category.SPORT),
        Word("Булит", Category.SPORT),
        Word("Рекорд", Category.SPORT),
    )

    fun getWords() = remoteProvider.subscribeToAllWords()
    fun saveWord(word:Word) = remoteProvider.saveWord(word)
    fun getWordByName (name: String) = remoteProvider.getWordByName(name)
}