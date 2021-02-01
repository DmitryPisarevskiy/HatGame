package com.dmitry.pisarevskiy.hatgame.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseFragment<S>: Fragment() {
    abstract val viewModel: BaseViewModel<S>
    abstract val layoutRes:Int

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(layoutRes, container, false)

        viewModel.viewState()
            .observe(this, object: Observer<S> {
                override fun onChanged(t:S?) {
                    if (t == null) return
                    renderData(t)
                }
            })


        return view
    }

    abstract fun renderData(t: S)
}