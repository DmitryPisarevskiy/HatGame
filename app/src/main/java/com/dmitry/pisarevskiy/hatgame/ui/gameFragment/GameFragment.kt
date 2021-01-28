package com.dmitry.pisarevskiy.hatgame.ui.gameFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dmitry.pisarevskiy.hatgame.R
import com.dmitry.pisarevskiy.hatgame.data.Repository
import com.dmitry.pisarevskiy.hatgame.data.model.GameTypes

const val GAME_FRAGMENT_TYPE_OF_GAME = "new or saved game?"

class GameFragment : Fragment() {
    private lateinit var gameType: String
    private lateinit var viewModel: GameViewModel

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
        val tvWord: TextView = view.findViewById(R.id.tv_word)

        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        if (gameType==GameTypes.NEW.type) {viewModel.changeGameModeToNew()}
        viewModel.viewState()
            .observe(this, Observer<GameViewState> { t -> t?.let { tvWord.text = it.currentWord } })

        btnGuessed.setOnClickListener {
            viewModel.nextWord(true)
//            tvWord.text = viewModel.viewState().value!!.game.currentWord.name
        }

        btnNotGuessed.setOnClickListener {
            viewModel.nextWord(false)
//            tvWord.text = viewModel.viewState().value!!.game.currentWord.name
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(gameType:String) =
            GameFragment().apply {
                arguments = Bundle().apply {
                    putString(GAME_FRAGMENT_TYPE_OF_GAME, gameType)
                }
            }
    }
}