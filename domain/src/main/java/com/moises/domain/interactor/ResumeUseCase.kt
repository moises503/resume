package com.moises.domain.interactor

import com.moises.domain.UseCase
import com.moises.domain.entity.Resume
import com.moises.domain.executor.JobScheduler
import com.moises.domain.executor.UIScheduler
import com.moises.domain.repository.ResumeRepository
import io.reactivex.Single

class ResumeUseCase(private val repository: ResumeRepository, uiScheduler: UIScheduler, jobScheduler: JobScheduler)
    : UseCase<Resume, Unit>(uiScheduler, jobScheduler){


    override fun buildUseCaseObservable(params: Unit): Single<Resume> {
        return Single.create { emitter ->
            try {
                val authInfo = repository.attemptGetResume()
                emitter.onSuccess(authInfo)
            } catch (e : Exception) {
                if (!emitter.isDisposed) {
                    emitter.onError(e)
                }
            }
        }
    }

}