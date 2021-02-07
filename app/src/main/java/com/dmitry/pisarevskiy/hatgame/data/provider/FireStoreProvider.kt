package com.dmitry.pisarevskiy.hatgame.data.provider

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dmitry.pisarevskiy.hatgame.data.WordResult
import com.dmitry.pisarevskiy.hatgame.data.errors.NoAuthException
import com.dmitry.pisarevskiy.hatgame.data.model.User
import com.dmitry.pisarevskiy.hatgame.data.model.Word
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.*

const val USERS_COLLECTION: String = "users"
const val WORDS_COLLECTION: String = "words"

class FireStoreProvider : RemoteDataProvider {
    private val TAG = "${FireStoreProvider::class.java.simpleName} :"
    private val db = FirebaseFirestore.getInstance()
    private val currentUser
        get() = FirebaseAuth.getInstance().currentUser

    override fun subscribeToAllWords(): LiveData<WordResult> = MutableLiveData<WordResult>().apply {
        try {
            //val wordsRef = db.collection(GameType.NEW.type)
            getUserWordsCollection().addSnapshotListener { snapshot, error ->
                value = error?.let { WordResult.Error(it) }
                    ?: snapshot?.let {
                        val words = it.documents.map { docSnapshot ->
                            docSnapshot.toObject(Word::class.java)
                        }
                        WordResult.Success(words)
                    }
            }
        } catch (e:Throwable) {
            value = WordResult.Error(e)
        }

    }

    override fun subscribeToSavedGame(gameID: String): LiveData<WordResult> =
        MutableLiveData<WordResult>().apply {
            //val wordsRef = db.collection(gameID)
            try {
                getUserWordsCollection().addSnapshotListener { snapshot, error ->
                    value = error?.let { WordResult.Error(it) }
                        ?: snapshot?.let {
                            val words = it.documents.map { docSnapshot ->
                                docSnapshot.toObject(Word::class.java)
                            }
                            WordResult.Success(words)
                        }
                }
            } catch (e: Throwable) {
                value = WordResult.Error(e)
            }

        }

    override fun getWordByName(gameID: String, name: String): LiveData<WordResult> =
        MutableLiveData<WordResult>().apply {
            //val wordsRef = db.collection(gameID)
            try {
                getUserWordsCollection().document(name).get()
                    .addOnSuccessListener { snapshot ->
                        value = WordResult.Success(snapshot.toObject(Word::class.java))
                    }.addOnFailureListener { exception ->
                        value = WordResult.Error(exception)
                    }
            } catch (e: Throwable) {
                value = WordResult.Error(e)
            }
        }

    override fun saveWord(gameID: String, word: Word): LiveData<WordResult> =
        MutableLiveData<WordResult>().apply {
            //val wordsRef = db.collection(gameID)
            try {
                getUserWordsCollection().document(word.name)
                    .set(word).addOnSuccessListener {
                        Log.d(TAG, "Word $word is saved")
                        value = WordResult.Success(word)
                    }
                    .addOnFailureListener { p0 ->
                        Log.d(TAG, "Error saving note $word, message: ${p0.message}")
                        value = WordResult.Error(p0)
                    }
            } catch (e:Throwable) {
                value = WordResult.Error(e)
            }
        }

    private fun getUserWordsCollection() = currentUser?.let {
        db.collection(USERS_COLLECTION).document(it.uid).collection(WORDS_COLLECTION)
    } ?: throw NoAuthException()

    override fun getCurrentUser():LiveData<User?> =
        MutableLiveData<User?>().apply {
            value=currentUser?.let {
                User(it.displayName ?:"",
                it.email?:"")
            }
        }
}
