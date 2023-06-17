package com.geekbrains.githubclient.rx

import io.reactivex.rxjava3.core.Observable

class Operators {
    fun exec() {

    }

    class Producer() {
        fun createJust() = Observable.just("1", "2", "3", "3")
    }

    class Consumer(producer: Producer) {
        fun exec() {

        }
    }
}