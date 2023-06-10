package com.geekbrains.githubclient.domain

import com.geekbrains.githubclient.data.Counters
import moxy.MvpPresenter

class MainPresenter(val counters: Counters) : MvpPresenter<MainView>() {
    fun counterOneClick() {
        val counterValue = counters.increase(0)
        viewState.setButtonOneText(counterValue.toString())
    }

    fun counterTwoClick() {
        val counterValue = counters.increase(1)
        viewState.setButtonTwoText(counterValue.toString())
    }

    fun counterTreeClick() {
        val counterValue = counters.increase(2)
        viewState.setButtonTreeText(counterValue.toString())
    }
}