package com.geekbrains.githubclient.rx

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers


class BackPressure {
    fun exec() {
        val producer = Producer()
        val consumer = Consumer(producer)
        consumer.consume()
    }

    class Producer() {
        val count = 10000000

        fun noBackPressure() = Observable.range(0, count)
            .subscribeOn(Schedulers.io())

        fun backPressure() = Flowable.range(0, count)
            .subscribeOn(Schedulers.io())
            .onBackpressureLatest()
    }

    class Consumer(val producer: Producer) {
        val pauseDurationInMillis = 1000L

        fun consume() {
            //consumeNoBackPressure()
            consumeBackPressure()
        }

        fun consumeNoBackPressure() {
            producer.noBackPressure()
                .observeOn(Schedulers.computation())
                .subscribe({
                    Thread.sleep(pauseDurationInMillis)
                    println(it.toString())
                }, {
                    println("onError: ${it.message}")
                })
        }

        fun consumeBackPressure() {
            producer.noBackPressure()
                .observeOn(Schedulers.computation(), false, 1)
                .subscribe({
                    Thread.sleep(pauseDurationInMillis)
                    println(it.toString())
                }, {
                    println("onError: ${it.message}")
                })
        }
    }
}