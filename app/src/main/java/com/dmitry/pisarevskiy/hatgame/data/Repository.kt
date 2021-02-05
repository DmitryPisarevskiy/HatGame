package com.dmitry.pisarevskiy.hatgame.data

import androidx.lifecycle.LiveData
import com.dmitry.pisarevskiy.hatgame.data.model.Category
import com.dmitry.pisarevskiy.hatgame.data.model.Game
import com.dmitry.pisarevskiy.hatgame.data.model.GameType
import com.dmitry.pisarevskiy.hatgame.data.model.Word
import com.dmitry.pisarevskiy.hatgame.data.WordResult.Success
import com.dmitry.pisarevskiy.hatgame.data.WordResult.Error
import com.dmitry.pisarevskiy.hatgame.data.provider.FireStoreProvider
import com.dmitry.pisarevskiy.hatgame.data.provider.RemoteDataProvider
import com.dmitry.pisarevskiy.hatgame.ui.gameFragment.NUM_OF_WORDS_IN_NEW_GAME

object Repository {
    lateinit var currentGame: Game
    lateinit var newGame: Game
    lateinit var savedGame: Game
    private val remoteProvider: RemoteDataProvider = FireStoreProvider()

    var extraSavedGame = Game(
        GameType.SAVED.type, mutableListOf(
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

    init {
        newGame = Game(
            id = GameType.NEW.type,
            list = words,
            numOfWords = NUM_OF_WORDS_IN_NEW_GAME
        )
        savedGame = extraSavedGame
        val newGameLiveData: LiveData<WordResult> = getAllWords()
        val savedGameLiveData: LiveData<WordResult> = getSavedGame()
        when (newGameLiveData.value) {
            is Success<*> -> {
                newGame = Game(
                    id = GameType.NEW.type,
                    list = (newGameLiveData.value as WordResult.Success<*>).data as MutableList<Word>,
                    numOfWords = NUM_OF_WORDS_IN_NEW_GAME
                )
            }
            is Error -> {
            }
        }
        when (savedGameLiveData.value) {
            is Success<*> -> {
                savedGame = Game(
                    id = GameType.SAVED.type,
                    list = (newGameLiveData.value as WordResult.Success<*>).data as MutableList<Word>,
                )
            }
            is Error -> {
            }
        }
    }

    fun getAllWords(): LiveData<WordResult> = remoteProvider.subscribeToAllWords()
    fun getSavedGame(): LiveData<WordResult> =
        remoteProvider.subscribeToSavedGame(GameType.SAVED.type)

    fun saveWord(gameID: String, word: Word): LiveData<WordResult> =
        remoteProvider.saveWord(gameID, word)

    fun getWordByName(gameID: String, name: String): LiveData<WordResult> =
        remoteProvider.getWordByName(gameID, name)
}