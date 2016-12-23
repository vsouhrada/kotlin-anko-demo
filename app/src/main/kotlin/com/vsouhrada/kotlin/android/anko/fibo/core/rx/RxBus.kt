package com.vsouhrada.kotlin.android.anko.fibo.core.rx

import rx.Observable
import rx.Subscription
import rx.functions.Action1
import rx.lang.kotlin.PublishSubject
import rx.subjects.SerializedSubject

/**
 * @author vsouhrada
 * @since 0.1
 */
class RxBus {

    private val bus = SerializedSubject<Any, Any>(PublishSubject())

    fun send(o: Any) {
        bus.onNext(o)
    }

    fun toObservable(): Observable<Any> {
        return bus
    }

    fun <T> onEvent(clazz: Class<T>, handler: Action1<T>): Subscription {
        return bus.ofType(clazz).subscribe(handler)
    }

    fun <T> onEvent2(clazz: Class<T>, handler: Action1<T>): Subscription {
        return bus.ofType(clazz).subscribe(handler)
    }

    fun hasObservers(): Boolean {
        return bus.hasObservers()
    }

}