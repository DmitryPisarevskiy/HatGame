package com.dmitry.pisarevskiy.hatgame.data.provider

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dmitry.pisarevskiy.hatgame.data.WordResult
import com.dmitry.pisarevskiy.hatgame.data.model.GameType
import com.dmitry.pisarevskiy.hatgame.data.model.Word
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot


class SimpleProvider {
    private val TAG = "${SimpleProvider::class.java.simpleName} :"
    private val db = FirebaseFirestore.getInstance()

    fun subscribeToAllWords(): MutableList<Word> {
        val db = FirebaseFirestore.getInstance()
        val words: MutableList<Word> = mutableListOf()
        db.collection(GameType.NEW.type)
            .get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
//                    val words = task.result?.documents?.map { docSnapshot ->
//                        docSnapshot.toObject(Word::class.java)
//                    }
                    for (document in task.result!!) {
                        words.add(document.toObject(Word::class.java))
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            })
        return words
    }
}