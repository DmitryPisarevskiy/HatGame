package com.dmitry.pisarevskiy.hatgame.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dmitry.pisarevskiy.hatgame.data.model.Category
import com.dmitry.pisarevskiy.hatgame.data.model.Game
import com.dmitry.pisarevskiy.hatgame.data.model.GameType
import com.dmitry.pisarevskiy.hatgame.data.model.Word
import com.dmitry.pisarevskiy.hatgame.data.provider.FireStoreProvider
import com.dmitry.pisarevskiy.hatgame.data.provider.RemoteDataProvider
import com.dmitry.pisarevskiy.hatgame.data.provider.SimpleProvider
import com.dmitry.pisarevskiy.hatgame.ui.gameFragment.NUM_OF_WORDS_IN_NEW_GAME
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

const val NEW_GAME_ID = "NEW"

object Repository {
    var currentGame: Game? = null
    val gameLiveData = MutableLiveData<Game>()
    var gameIsReady: Boolean = false
    private val remoteProvider: RemoteDataProvider = FireStoreProvider()
    private val simpleProvider: SimpleProvider = SimpleProvider()

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

    fun getAllWords(): MutableList<Word> = simpleProvider.subscribeToAllWords()
    fun getSavedGame(): LiveData<WordResult> =
        remoteProvider.subscribeToSavedGame(GameType.SAVED.type)

    fun saveWord(gameID: String, word: Word): LiveData<WordResult> =
        remoteProvider.saveWord(gameID, word)

    fun getWordByName(gameID: String, name: String): LiveData<WordResult> =
        remoteProvider.getWordByName(gameID, name)

    fun getGame(gameID: String= NEW_GAME_ID) {
        val mWords: MutableList<Word> = mutableListOf()
        val db = FirebaseFirestore.getInstance()
        db.collection(gameID)
            .get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        println(document.id + " => " + document.data)
                        mWords.add(document.toObject(Word::class.java))
                    }
                    if (mWords.size > 0) {
                        currentGame = Game(
                            id = GameType.NEW.type,
                            list = mWords,
                            numOfWords = NUM_OF_WORDS_IN_NEW_GAME
                        )
                        gameLiveData.value = currentGame
                        gameIsReady = true
                    }
                } else {
                    Log.w("TAG", "Error getting documents.", task.exception)
                }
            })
    }

}