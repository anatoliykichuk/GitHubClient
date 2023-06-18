package com.geekbrains.githubclient.rx

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Creation {
    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer() {
        fun just(): Observable<String> {
            return Observable.just("1", "2", "3")
        }

        fun fromIterable(): Observable<String> {
            return Observable.just("1", "2", "3")
        }

        fun interval() = Observable.interval(1, TimeUnit.SECONDS)

        fun randomResultOperation(): Boolean {
            Thread.sleep(Random.nextLong(1000))
            return listOf(true, false, true)[Random.nextInt(2)]
        }

        fun fromCallable() = Observable.fromCallable {
            val result = randomResultOperation()
            return@fromCallable result
        }

        fun create() = Observable.create<String> { emitter ->
            try {
                for (i in 0..10) {
                    randomResultOperation().let {
                        if (it) {
                            emitter.onNext("Success$i")
                        } else {
                            emitter.onError(RuntimeException("Error"))
                            return@create
                        }
                    }
                }

            } catch (t: Throwable) {
                emitter.onError(RuntimeException("Error"))
            }
        }
    }

    class Consumer(val producer: Producer) {
        val stringObserver = object : Observer<String> {
            var disposable: Disposable? = null

            override fun onSubscribe(d: Disposable) {
                disposable = d
                println("onSubscribe")
            }

            override fun onNext(s: String) {
                println("onNext: $s")
            }

            override fun onError(e: Throwable) {
                println("onError: ${e?.message}")
            }

            override fun onComplete() {
                println("onComplete")
            }
        }

        fun exec() {
            execJust()
        }

        fun execJust() {
            producer.just().subscribe(stringObserver)
        }

        fun execFromIterable() {
            producer.fromIterable().subscribe(stringObserver)
        }

        fun execInterval() {
            producer.interval().subscribe {
                println("onNext: $it")
            }
        }
    }
}