package com.moises.presentation.core

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter(private val disposables: CompositeDisposable = CompositeDisposable()) {

    fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }

    fun destroy() {
        disposables.dispose()
    }

}