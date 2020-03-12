package com.moises.domain.core

import io.reactivex.observers.DisposableSingleObserver

abstract class SingleObserver<T> : DisposableSingleObserver<T>() {
    override fun onSuccess(t: T) = Unit
    override fun onError(e: Throwable) = Unit
}
