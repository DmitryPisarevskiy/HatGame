package com.dmitry.pisarevskiy.hatgame.ui.gameFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.dmitry.pisarevskiy.hatgame.R
import com.dmitry.pisarevskiy.hatgame.data.NEW_GAME_ID
import com.dmitry.pisarevskiy.hatgame.data.model.GameType
import com.dmitry.pisarevskiy.hatgame.ui.base.BaseFragment
import com.dmitry.pisarevskiy.hatgame.ui.resultsFragment.ResultsFragment

const val GAME_FRAGMENT_TYPE_OF_GAME = "new or saved game?"

class GameFragment : BaseFragment<GameViewState>() {
    private lateinit var gameType: String
    override val viewModel: GameViewModel by lazy {
        ViewModelProvider(this).get(GameViewModel::class.java)
    }
    private lateinit var tvWord: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gameType = it.getString(GAME_FRAGMENT_TYPE_OF_GAME)!!
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_game, container, false)
        val btnGuessed: Button = view.findViewById(R.id.btn_guessed)
        val btnNotGuessed: Button = view.findViewById(R.id.btn_not_guessed)
        tvWord = view.findViewById(R.id.tv_word)

        if (gameType == GameType.NEW.type) {
            viewModel.getGame(NEW_GAME_ID)
        } else {
            viewModel.getGame("SAVED")
        }

        btnGuessed.setOnClickListener {
            viewModel.nextWord(true)
        }

        btnNotGuessed.setOnClickListener {
            viewModel.nextWord(false)
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(gameType: String) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putString(GAME_FRAGMENT_TYPE_OF_GAME, gameType)
                }
            }
    }

    override fun renderState(state: GameViewState) {
        if (state.gameIsOver) {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.mainFrame, ResultsFragment.newInstance())
                ?.commitNow()
        } else if (state.gameIsReady) {
            tvWord.text = state.currentWord
        }
    }
}