package com.dmitry.pisarevskiy.hatgame

import android.util.Log
import com.dmitry.pisarevskiy.hatgame.data.Repository
import com.dmitry.pisarevskiy.hatgame.data.model.GameType
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import org.junit.Assert
import org.junit.Test


class DataBaseTest {
    @Test
    fun dbTest() {
        val TAG = "tag"
        val db = FirebaseFirestore.getInstance()
        val wordsRef = db.collection(GameType.NEW.type)
        val user: MutableMap<String, Any> = HashMap()
        for (word in Repository.words) {
            wordsRef.document(word.name)
                .set(word).addOnSuccessListener {
                    Log.d(TAG, "Word $word is saved")
                }
                .addOnFailureListener { p0 ->
                    Log.d(TAG, "Error saving note $word, message: ${p0.message}")
                }
        }
        Assert.assertEquals(4, 2 + 2)
    }

    @Test
    fun dbReadTest() {
        val TAG = "tag"
        val db = FirebaseFirestore.getInstance()
        db.collection("users")
            .get()
            .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                if (task.isSuccessful) {
                    for (document in task.result!!) {
                        Log.d(TAG, document.id + " => " + document.data)
                        println(document.id + " => " + document.data)
                    }
                } else {
                    Log.w(TAG, "Error getting documents.", task.exception)
                }
            })
        Assert.assertEquals(4, 2 + 2)
    }
}