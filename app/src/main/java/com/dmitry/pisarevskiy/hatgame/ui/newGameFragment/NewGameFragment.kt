package com.dmitry.pisarevskiy.hatgame.ui.newGameFragment

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
import com.dmitry.pisarevskiy.hatgame.ui.mainFragment.MainViewState

class NewGameFragment : Fragment() {
    private lateinit var viewModel: NewGameViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view:View = inflater.inflate(R.layout.fragment_new_game, container, false)
        val btnGuessed: Button = view.findViewById(R.id.btn_guessed)
        val btnNotGuessed: Button = view.findViewById(R.id.btn_not_guessed)
        val tvWord:TextView = view.findViewById(R.id.tv_word)

        viewModel = ViewModelProvider(this).get(NewGameViewModel::class.java)
        viewModel.viewState().observe(this, Observer<NewGameViewState> {t-> t?.let {tvWord.text = it.game.currentWord.name}
        })

        btnGuessed.setOnClickListener {
            Repository.game.currentWord.isPlayed = true
            Repository.game.currentWord.isGuessed = true
            Repository.game.currentWord = Repository.game.getNextWord()
        }

        btnNotGuessed.setOnClickListener {
            Repository.game.currentWord.isPlayed = true
            Repository.game.currentWord = viewModel.viewState().value!!.game.getNextWord()
            Repository.game.currentWord = viewModel.viewState().value!!.game.getNextWord()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            NewGameFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}