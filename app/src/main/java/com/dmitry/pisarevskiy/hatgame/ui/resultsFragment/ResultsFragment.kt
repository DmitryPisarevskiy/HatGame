package com.dmitry.pisarevskiy.hatgame.ui.resultsFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.dmitry.pisarevskiy.hatgame.R
import com.dmitry.pisarevskiy.hatgame.ui.base.BaseFragment
import com.dmitry.pisarevskiy.hatgame.ui.mainFragment.MainFragment

class ResultsFragment : BaseFragment<ResultsViewState>() {
    override val viewModel: ResultsViewModel by lazy {ViewModelProvider(this).get(ResultsViewModel::class.java)}
    private lateinit var tvResults: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_results, container, false)
        val button: Button = view.findViewById(R.id.btn_ok)
        tvResults = view.findViewById(R.id.tv_results)
        button.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.mainFrame, MainFragment.newInstance())
                ?.commitNow()
        }
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() = ResultsFragment()
    }

    override fun renderData(state: ResultsViewState) {
        tvResults.text =
            "${getString(R.string.number_of_guessed_words_is)}${state.guessedWords} из ${state.playedWords}"
    }
}