package com.geekbrains.githubclient.data

data class Counters(
    val data: MutableList<Int> = mutableListOf(0, 0, 0)
) {
    fun getByIndex(index: Int): Int = data[index]

    fun increase(index: Int): Int {
        data[index]++
        return getByIndex(index)
    }
}
