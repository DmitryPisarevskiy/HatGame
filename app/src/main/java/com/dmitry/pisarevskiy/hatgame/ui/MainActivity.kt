package com.dmitry.pisarevskiy.hatgame.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dmitry.pisarevskiy.hatgame.R
import com.dmitry.pisarevskiy.hatgame.ui.mainFragment.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.mainFrame, MainFragment.newInstance())
                .commitNow()
        }
    }

}