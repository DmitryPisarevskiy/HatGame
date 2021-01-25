package com.dmitry.pisarevskiy.hatgame.ui.mainFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.dmitry.pisarevskiy.hatgame.R
import com.dmitry.pisarevskiy.hatgame.ui.newGameFragment.NewGameFragment

class MainFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View =  inflater.inflate(R.layout.fragment_main, container, false)
        val btnNewGame: Button = view.findViewById(R.id.btn_new_game)
        btnNewGame.setOnClickListener{
            fragmentManager?.beginTransaction()
                ?.replace(R.id.mainFrame, NewGameFragment.newInstance())
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