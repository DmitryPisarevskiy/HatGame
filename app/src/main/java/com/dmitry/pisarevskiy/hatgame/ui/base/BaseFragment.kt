package com.dmitry.pisarevskiy.hatgame.ui.base

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer

abstract class BaseFragment<S : BaseViewState> : Fragment() {
    abstract val viewModel: BaseViewModel<S>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.viewState()
            .observe(this, Observer<S> { t ->
                t?.let {
                    renderData(t)
                }
            })
    }

    abstract fun renderData(state: S)
}