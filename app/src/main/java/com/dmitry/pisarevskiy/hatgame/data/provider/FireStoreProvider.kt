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

    override fun subscribeToAllWords(): LiveData<WordResult> {
        val result = MutableLiveData<WordResult>()
        val wordsRef = db.collection(GameType.NEW.type)
        wordsRef.addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(snapshot: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    result.value = WordResult.Error(error)
                } else if (snapshot != null) {
                    val words = mutableListOf<Word>()
                    for (doc:QueryDocumentSnapshot in snapshot) {
                        words.add(doc.toObject(Word::class.java))
                    }
                    result.value = WordResult.Success(words)
                }
            }
        })
        return result
    }

    override fun subscribeToSavedGame(gameID: String): LiveData<WordResult> {
        val result = MutableLiveData<WordResult>()
        val wordsRef = db.collection(gameID)

        wordsRef.addSnapshotListener(object : EventListener<QuerySnapshot> {
            override fun onEvent(snapshot: QuerySnapshot?, error: FirebaseFirestoreException?) {
                if (error != null) {
                    result.value = WordResult.Error(error)
                } else if (snapshot != null) {
                    val words = mutableListOf<Word>()
                    for (doc:QueryDocumentSnapshot in snapshot) {
                        words.add(doc.toObject(Word::class.java))
                    }
                    result.value = WordResult.Success(words)
                }
            }
        })
        return result
    }

    override fun getWordByName(gameID: String, name:String): LiveData<WordResult> {
        val result = MutableLiveData<WordResult>()
        val wordsRef = db.collection(gameID)

        wordsRef.document(name).get()
            .addOnSuccessListener { snapshot ->
                result.value = WordResult.Success(snapshot.toObject(Word::class.java))
            }.addOnFailureListener { exception ->
                result.value = WordResult.Error(exception)
            }
        return result
    }

    override fun saveWord(gameID: String, word: Word): LiveData<WordResult> {
        val result = MutableLiveData<WordResult>()
        val wordsRef = db.collection(gameID)

        wordsRef.document(word.name)
            .set(word).addOnSuccessListener {
                Log.d(TAG, "Word $word is saved")
                result.value = WordResult.Success(word)
            }
            .addOnFailureListener { p0 ->
                Log.d(TAG, "Error saving note $word, message: ${p0.message}")
                result.value = WordResult.Error(p0)
            }
        return result
    }


}