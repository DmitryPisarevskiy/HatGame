package com.dmitry.pisarevskiy.hatgame.ui.resultsFragment

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
import com.dmitry.pisarevskiy.hatgame.ui.mainFragment.MainFragment

class ResultsFragment : Fragment() {
    private lateinit var viewModel: ResultsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_results, container, false)
        val button: Button = view.findViewById(R.id.btn_ok)
        val tvResults: TextView = view.findViewById(R.id.tv_results)

        viewModel = ViewModelProvider(this).get(ResultsViewModel::class.java)
        viewModel.viewState()
            .observe(this, Observer<ResultsViewState> { t ->
                t?.let {
                    tvResults.text =
                        "${getString(R.string.number_of_guessed_words_is)}${it.numOfGuessedWords} из ${it.numOfWords}"
                }
            })

        button.setOnClickListener {
            fragmentManager?.beginTransaction()
                ?.replace(R.id.mainFrame, MainFragment.newInstance())
                ?.commitNow()
        }

        return view
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ResultsFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}