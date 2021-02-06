package com.dmitry.pisarevskiy.hatgame.data.provider

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dmitry.pisarevskiy.hatgame.data.WordResult
import com.dmitry.pisarevskiy.hatgame.data.model.GameType
import com.dmitry.pisarevskiy.hatgame.data.model.Word
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.*
import java.lang.Exception

class FireStoreProvider : RemoteDataProvider {
    private val TAG = "${FireStoreProvider::class.java.simpleName} :"
    private val db = FirebaseFirestore.getInstance()

    override fun subscribeToAllWords(): LiveData<WordResult> = MutableLiveData<WordResult>().apply {
        val wordsRef = db.collection(GameType.NEW.type)
        wordsRef.addSnapshotListener { snapshot, error ->
            value = error?.let { WordResult.Error(it) }
                ?: snapshot?.let {
                    val words = it.documents.map { docSnapshot ->
                        docSnapshot.toObject(Word::class.java)
                    }
                    WordResult.Success(words)
                }
        }
    }

    override fun subscribeToSavedGame(gameID: String): LiveData<WordResult> =
        MutableLiveData<WordResult>().apply {
            val wordsRef = db.collection(gameID)
            wordsRef.addSnapshotListener { snapshot, error ->
                value = error?.let { WordResult.Error(it) }
                    ?: snapshot?.let {
                        val words = it.documents.map { docSnapshot ->
                            docSnapshot.toObject(Word::class.java)
                        }
                        WordResult.Success(words)
                    }
            }
        }

    override fun getWordByName(gameID: String, name: String): LiveData<WordResult> =
        MutableLiveData<WordResult>().apply {
            val wordsRef = db.collection(gameID)
            wordsRef.document(name).get()
                .addOnSuccessListener { snapshot ->
                    value = WordResult.Success(snapshot.toObject(Word::class.java))
                }.addOnFailureListener { exception ->
                    value = WordResult.Error(exception)
                }
        }

    override fun saveWord(gameID: String, word: Word): LiveData<WordResult> =
        MutableLiveData<WordResult>().apply {
            val wordsRef = db.collection(gameID)
            wordsRef.document(word.name)
                .set(word).addOnSuccessListener {
                    Log.d(TAG, "Word $word is saved")
                    value = WordResult.Success(word)
                }
                .addOnFailureListener { p0 ->
                    Log.d(TAG, "Error saving note $word, message: ${p0.message}")
                    value = WordResult.Error(p0)
                }
        }


}