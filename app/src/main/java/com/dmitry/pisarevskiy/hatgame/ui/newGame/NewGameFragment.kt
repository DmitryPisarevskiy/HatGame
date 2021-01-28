package com.dmitry.pisarevskiy.hatgame.ui.newGame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dmitry.pisarevskiy.hatgame.R


class BlankFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_game, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            BlankFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}