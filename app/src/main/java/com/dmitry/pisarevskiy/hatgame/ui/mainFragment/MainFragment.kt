package com.dmitry.pisarevskiy.hatgame.ui.mainFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import com.dmitry.pisarevskiy.hatgame.R
import com.dmitry.pisarevskiy.hatgame.data.model.GameType
import com.dmitry.pisarevskiy.hatgame.ui.base.BaseFragment
import com.dmitry.pisarevskiy.hatgame.ui.gameFragment.GameFragment

class MainFragment : BaseFragment<MainViewState>() {
    override val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        val btnNewGame: Button = view.findViewById(R.id.btn_new_game)
        val btnLoadGame: Button = view.findViewById(R.id.btn_load_game)

        btnLoadGame.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.mainFrame, GameFragment.newInstance(GameType.SAVED.type))
                ?.commitNow()
        }
        btnNewGame.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.mainFrame, GameFragment.newInstance(GameType.NEW.type))
                ?.commitNow()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = MainFragment()
    }

    override fun renderData(state: MainViewState) {
    }
}
