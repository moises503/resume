package com.moises.data.usecase

import com.moises.data.model.Profile
import com.moises.domain.UseCase
import com.moises.domain.executor.JobScheduler
import com.moises.domain.executor.UIScheduler
import com.moises.data.provider.ResumeProvider
import io.reactivex.Single

class ResumeUseCase(private val provider: ResumeProvider, uiScheduler: UIScheduler, jobScheduler: JobScheduler)
    : UseCase<Profile, Unit>(uiScheduler, jobScheduler){


    override fun buildUseCaseObservable(params: Unit): Single<Profile> {
        return Single.create { emitter ->
            try {
                val authInfo = provider.attemptGetResume()
                emitter.onSuccess(authInfo)
            } catch (e : Exception) {
                if (!emitter.isDisposed) {
                    emitter.onError(e)
                }
            }
        }
    }

}