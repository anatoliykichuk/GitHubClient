package com.geekbrains.githubclient.domain

import com.geekbrains.githubclient.data.Counters

class MainPresenter(val view: MainView) {
    val counters = Counters()

    fun onViewClick(id: Int, idToIndex: Map<Int, Int>) {
        idToIndex.get(id)?.let {
            view.setButtonText(it, counters.increase(it).toString())
        }
    }
}