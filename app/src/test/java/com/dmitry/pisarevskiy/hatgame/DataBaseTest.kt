package com.dmitry.pisarevskiy.hatgame

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import org.junit.Test


class DataBaseTest {
    @Test
    fun dataBaseWriting() {
        val TAG = "tag"
        val db = FirebaseFirestore.getInstance()
        val user: MutableMap<String, Any> = HashMap()
        user["first"] = "Ada"
        user["last"] = "Lovelace"
        user["born"] = 1815

        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(
                    TAG,
                    "DocumentSnapshot added with ID: " + documentReference.id
                )
            }
            .addOnFailureListener { e -> Log.w(TAG, "Error adding document", e) }
    }
}