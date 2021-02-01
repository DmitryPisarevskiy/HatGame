package com.dmitry.pisarevskiy.hatgame.ui.mainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.dmitry.pisarevskiy.hatgame.R
import com.dmitry.pisarevskiy.hatgame.data.model.GameTypes
import com.dmitry.pisarevskiy.hatgame.ui.gameFragment.GAME_FRAGMENT_TYPE_OF_GAME
import com.dmitry.pisarevskiy.hatgame.ui.gameFragment.GameFragment

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        val btnNewGame: Button = view.findViewById(R.id.btn_new_game)
        val btnLoadGame: Button = view.findViewById(R.id.btn_load_game)

        btnLoadGame.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.mainFrame, GameFragment.newInstance(GameTypes.SAVED.type))
                ?.commitNow()
        }
        btnNewGame.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.mainFrame, GameFragment.newInstance(GameTypes.NEW.type))
                ?.commitNow()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            MainFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}