package com.geekbrains.githubclient.rx

import io.reactivex.rxjava3.core.Observable
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Operators {
    fun exec() {

    }

    class Producer() {
        fun createJust() = Observable.just("1", "2", "3", "3")
        fun createJust2() = Observable.just("4", "5", "6")
    }

    class Consumer(val producer: Producer) {
        fun exec() {

        }

        fun execTake() {
            val count = 2L

            producer.createJust()
                .take(count)
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }

        fun execSkip() {
            val count = 2L

            producer.createJust()
                .skip(count)
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }

        fun execMap() {
            producer.createJust()
                .map { it + it }
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }

        fun execDistinct() {
            producer.createJust()
                .distinct()
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }

        fun execFilter() {
            producer.createJust()
                .filter() { it.toInt() > 1 }
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }

        fun execMerge() {
            producer.createJust()
                .mergeWith(producer.createJust2())
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }

        fun execFlatMap() {
            producer.createJust()
                .flatMap {
                    val delay = Random.nextInt(1000).toLong()
                    return@flatMap Observable.just("${it}x").delay(delay, TimeUnit.MILLISECONDS)
                }
                .subscribe({ s ->
                    println("onNext: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }
    }
}