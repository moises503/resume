package com.moises.domain.core

import com.moises.domain.core.executor.JobScheduler
import com.moises.domain.core.executor.UIScheduler
import io.reactivex.schedulers.Schedulers
import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver
import java.util.concurrent.TimeUnit

abstract class ObservableUseCase<Params, T>(
    private val uiScheduler: UIScheduler,
    private val jobScheduler: JobScheduler
) : UseCase<Params, Observable<T>>() {

    fun execute(params: Params? = null): Observable<T> {
        return buildUseCase(params)
            .subscribeOn(Schedulers.from(jobScheduler))
            .observeOn(uiScheduler.getScheduler())
    }

    fun execute(params: Params?, observer: DisposableObserver<T>) {
        val observable = buildUseCase(params)
            .subscribeOn(Schedulers.from(jobScheduler))
            .observeOn(uiScheduler.getScheduler())
            .debounce(400, TimeUnit.MILLISECONDS)
        subscribe(observable.subscribeWith(observer))
    }
}