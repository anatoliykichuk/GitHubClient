package com.geekbrains.githubclient.rx

import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class Sources {

    fun exec() {
        Consumer(Producer()).exec()
    }

    class Producer() {
        fun randomResultOperation(): Boolean {
            Thread.sleep(Random.nextLong(1000))
            return listOf(true, false, true)[Random.nextInt(2)]
        }

        fun completable() = Completable.create { emitter ->
            randomResultOperation().let {
                if (it) {
                    emitter.onComplete()
                } else {
                    emitter.onError(RuntimeException("Error"))
                    return@create
                }
            }
        }

        fun single() = Single.fromCallable {
            return@fromCallable "Some callable value"
        }

        fun maybe() = Maybe.create<String> { emitter ->
            randomResultOperation().let {
                if (it) {
                    emitter.onSuccess("Success: $it")
                } else {
                    emitter.onComplete()
                    return@create
                }
            }
        }

        fun hotObservable() = Observable.interval(1, TimeUnit.SECONDS).publish()

        fun publishSubject() = PublishSubject.create<String>().apply {
            Observable.timer(2, TimeUnit.SECONDS)
                .subscribe {
                    onNext("Value from subject")
                }
        }
    }

    class Consumer(val producer: Producer) {
        fun exec() {

        }

        fun execCompletable() {
            producer.completable()
                .subscribe(
                    {
                        println("onComplete")
                    }, {
                        println("onError: ${it.message}")
                    })
        }

        fun execSingle() {
            producer.single()
                .map { it + it }
                .subscribe({ s ->
                    println("onSuccess: $s")
                }, {
                    println("onError: ${it.message}")
                })
        }

        fun execMaybe() {
            producer.maybe()
                .map { it + it }
                .subscribe({ s ->
                    println("onSuccess: $s")
                }, {
                    println("onError: ${it.message}")
                }, {
                    println("onComplete")
                })
        }

        fun execHotObservable() {
            val hotObservable = producer.hotObservable()

            hotObservable.subscribe {
                println(it)
            }

            hotObservable.connect()

            Thread.sleep(3000)

            hotObservable.subscribe {
                println("delayed: $it")
            }
        }

        fun execPublishSubject() {
            val subject = producer.publishSubject()

            subject.subscribe({
                println("onNext: $it")
            }, {
                println("onError: ${it.message}")
            })

            subject.onNext("from exec")
        }
    }
}