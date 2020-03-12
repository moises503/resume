package com.moises.domain.core

import io.reactivex.observers.DisposableObserver

abstract class ObservableObserver<T> : DisposableObserver<T>() {
    override fun onComplete() = Unit
    override fun onError(e: Throwable?) = Unit
    override fun onNext(value: T)  = Unit
}