package com.moises.domain.core

import io.reactivex.disposables.Disposable

abstract class UseCase<Params, Observable : Any> {

    private var disposable: Disposable? = null

    protected abstract fun buildUseCase(params: Params? = null): Observable

    fun dispose() {
        disposable?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
    }

    protected fun subscribe(subscription: Disposable? = null) {
        disposable = subscription
    }

}